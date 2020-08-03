package com.example.authcenter.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * account_role_relation
 *
 * @author xujw
 * @since 2020-07-30 15:55:06
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class AccountRoleRelation implements Serializable {
    private static final long serialVersionUID = -5108558317443540853L;
    /**
     * 用户id
     */
    private Long uid;

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
}