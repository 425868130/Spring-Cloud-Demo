package com.example.authcenter.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * account_secret_profile
 *
 * @author xujw
 * @since 2020-07-31 15:47:17
 */
@Data
public class AccountSecretProfile implements Serializable {
    private static final long serialVersionUID = 7757929369674129798L;
    /**
     * 用户唯一标识
     */
    private Long uid;

    /**
     * 密码
     */
    private String password;

    /**
     * 身份证信息,加密存储
     */
    private String idCard;

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