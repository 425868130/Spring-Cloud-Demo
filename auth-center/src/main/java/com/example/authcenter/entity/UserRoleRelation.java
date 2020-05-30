package com.example.authcenter.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * user_role_relation
 *
 * @author xujw
 * @since 2020-5-23 11:06:36
 */
@Data
public class UserRoleRelation implements Serializable {
    /**
     * 用户id
     */
    private String uid;

    /**
     * 角色id
     */
    private Long roleId;

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