package com.example.authcenter.service.account;

import com.example.authcenter.entity.AccountSecretProfile;

/**
 * @author xujw
 * @since 2020-07-31 16:03:49
 * 账号机密资料校验及相关操作方法
 */
public interface AccountSecretProfileService {
    /**
     * 创建新配置信息
     *
     * @param secretProfile 加密信息对象
     */
    void createProfile(AccountSecretProfile secretProfile);

    /**
     * 用户密码校验
     *
     * @param uid      要校验密码的账号id
     * @param password 要校验的密码(已加密)
     * @return 校验是否通过
     */
    boolean validationAccountPassword(long uid, String password);

    /**
     * 更新账号密码
     *
     * @param uid         要操作的账号id
     * @param newPassword 新密码
     */
    void updatePassword(long uid, String newPassword);

    /**
     * 更新账号身份证信息
     *
     * @param uid       要操作的账号id
     * @param newIdCard 新的身份证信息(已加密)
     */
    void updateIdCard(long uid, String newIdCard);
}
