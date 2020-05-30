package com.example.authcenter.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * sys_auth_profile
 *
 * @author xujw
 * @since 2020-5-23 11:01:00
 */
@Data
public class SysAuthProfile implements Serializable {
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

    private static final long serialVersionUID = 1L;
}