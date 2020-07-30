package com.example.authcenter.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * account_auth_profile
 *
 * @author xujw
 * @since 2020-07-30 15:38:46
 */
@Data
public class AccountAuthProfile implements Serializable {
    private static final long serialVersionUID = -6992456299506346246L;
    /**
     * 用户唯一标识
     */
    private String uid;

    /**
     * 账户名
     */
    private String accountName;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码加密盐
     */
    private String salt;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}