package com.example.authcenter.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * group_role_relation
 *
 * @author xujw
 * @since 2020-5-23 11:01:00
 */
@Data
public class GroupRoleRelation implements Serializable {
    private static final long serialVersionUID = -1478280122756053355L;
    /**
     * 小组id
     */
    private Long groupId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 状态定义
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}