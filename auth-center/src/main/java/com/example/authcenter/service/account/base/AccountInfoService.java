package com.example.authcenter.service.account.base;

import com.example.authcenter.service.account.ao.AccountCreateForm;
import com.example.authcenter.service.account.ao.AccountUpdateForm;
import com.example.authcenter.service.account.bo.AccountInfoBo;

/**
 * 账号信息相关操作接口
 *
 * @author xujw
 * @since 2020-08-03 13:37:01
 */
public interface AccountInfoService {
    /**
     * 创建账号
     *
     * @param form 账号信息表单
     */
    void createAccount(AccountCreateForm form);

    /**
     * 删除指定账号
     *
     * @param uid 要删除的账号id
     */
    void deleteAccount(long uid);

    /**
     * 更新账号信息
     *
     * @param form 新的账号信息表单
     */
    void updateAccount(AccountUpdateForm form);

    /**
     * 通过uid获取账号信息对象
     *
     * @param uid 要获取的账号id
     * @return 账号信息对象BO封装
     */
    AccountInfoBo getByUid(long uid);

    /**
     * 通过账号名获取用户账号信息
     *
     * @param accountName 账号名
     * @return 账号信息对象BO封装
     */
    AccountInfoBo getByAccountName(String accountName);
}
