package com.example.authcenter.service.account;

import com.example.authcenter.dao.AccountSecretProfileDao;
import com.example.authcenter.entity.AccountSecretProfile;
import com.example.authcenter.service.account.base.AccountSecretProfileService;
import com.example.common.define.ServiceException;
import com.example.common.util.HashEncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.example.common.define.StatusCode.*;

@Slf4j
@Service
public class AccountSecretProfileServiceImpl implements AccountSecretProfileService {
    private final AccountSecretProfileDao accountSecretProfileDao;

    public AccountSecretProfileServiceImpl(AccountSecretProfileDao accountSecretProfileDao) {
        this.accountSecretProfileDao = accountSecretProfileDao;
    }

    @Override
    public void createProfile(AccountSecretProfile secretProfile) {
        if (secretProfile == null || secretProfile.getUid() == null || StringUtils.isEmpty(secretProfile.getPassword())) {
            throw new ServiceException(PARAM_INVALID, "非法的用户信息");
        }
        secretProfile.setSalt(HashEncryptUtil.getSalt());
        secretProfile.setPassword(HashEncryptUtil.encryptWithSalt(secretProfile.getPassword(), secretProfile.getSalt()));
        accountSecretProfileDao.insertSelective(secretProfile);
    }

    @Override
    public boolean validationAccountPassword(long uid, String password) {
        if (StringUtils.isEmpty(password)) {
            return false;
        }
        String accountSalt = accountSecretProfileDao.selectSaltByPrimaryKey(uid);
        if (StringUtils.isEmpty(accountSalt)) {
            throw new ServiceException(ACCOUNT_NOT_EXIST);
        }
        return accountSecretProfileDao.countByUidPassword(uid, HashEncryptUtil.encryptWithSalt(password, accountSalt)) > 0;
    }

    @Override
    public void updatePassword(long uid, String newPassword) {
        if (StringUtils.isEmpty(newPassword)) {
            return;
        }
        String accountSalt = accountSecretProfileDao.selectSaltByPrimaryKey(uid);
        if (StringUtils.isEmpty(accountSalt)) {
            throw new ServiceException(ACCOUNT_NOT_EXIST);
        }
        AccountSecretProfile update = new AccountSecretProfile();
        update.setUid(uid);
        update.setPassword(HashEncryptUtil.encryptWithSalt(newPassword, accountSalt));
        accountSecretProfileDao.updateByPrimaryKeySelective(update);
    }

    @Override
    public void updateIdCard(long uid, String newIdCard) {
        if (StringUtils.isEmpty(newIdCard)) {
            return;
        }
        AccountSecretProfile update = new AccountSecretProfile();
        update.setUid(uid);
        update.setIdCard(newIdCard);
        accountSecretProfileDao.updateByPrimaryKeySelective(update);
    }
}
