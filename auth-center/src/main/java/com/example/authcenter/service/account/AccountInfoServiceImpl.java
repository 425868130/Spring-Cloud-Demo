package com.example.authcenter.service.account;

import com.example.authcenter.dao.AccountInfoDao;
import com.example.authcenter.entity.AccountInfo;
import com.example.authcenter.service.account.ao.AccountCreateForm;
import com.example.authcenter.service.account.ao.AccountUpdateForm;
import com.example.authcenter.service.account.base.AccountInfoService;
import com.example.authcenter.service.account.base.AccountSecretProfileService;
import com.example.authcenter.service.account.bo.AccountInfoBo;
import com.example.authcenter.service.account.event.AccountInfoEvent;
import com.example.authcenter.service.account.mapper.AccountMapper;
import com.example.common.define.ServiceException;
import com.example.common.define.StatusCode;
import com.example.common.event.base.ServiceEventBus;
import com.example.common.util.SequenceGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Slf4j
@Service
@Validated
public class AccountInfoServiceImpl implements AccountInfoService {

    private final AccountMapper accountMapper = AccountMapper.INSTANCE;
    
    private final AccountInfoDao accountInfoDao;
    private final ServiceEventBus serviceEventBus;
    private final AccountSecretProfileService accountSecretProfileService;

    public AccountInfoServiceImpl(AccountInfoDao accountInfoDao, ServiceEventBus serviceEventBus,
                                  AccountSecretProfileService accountSecretProfileService) {
        this.accountInfoDao = accountInfoDao;
        this.serviceEventBus = serviceEventBus;
        this.accountSecretProfileService = accountSecretProfileService;
    }

    @Override
    @Transactional
    public void createAccount(@Valid AccountCreateForm form) {
        //todo 参数校验

        int existCount = accountInfoDao.countByAccount(form.getAccount());
        if (existCount > 0) {
            throw new ServiceException(StatusCode.TIMEOUT, "当前账号已存在!");
        }
        AccountInfo accountInfo = accountMapper.formToAccountInfo(form);
        accountInfo.setUid(SequenceGenerator.nextId());

        accountInfoDao.insertSelective(accountInfo);
        serviceEventBus.emit(AccountInfoEvent.onAdd(accountInfo));
    }

    @Override
    public void deleteAccount(long uid) {
        accountInfoDao.deleteByPrimaryKey(uid);
        serviceEventBus.emit(AccountInfoEvent.onDelete(uid));
    }

    @Override
    public void updateAccount(AccountUpdateForm form) {
        AccountInfo accountInfo = accountMapper.formToAccountInfo(form);
        serviceEventBus.emit(AccountInfoEvent.onUpdate(accountInfo));
    }

    @Override
    public AccountInfoBo getByUid(long uid) {
        return accountMapper.toAccountBo(accountInfoDao.selectByPrimaryKey(uid));
    }

    @Override
    public AccountInfoBo getByAccountName(String accountName) {
        if (StringUtils.isEmpty(accountName)) {
            return null;
        }
        return accountMapper.toAccountBo(accountInfoDao.getByAccount(accountName));
    }
}
