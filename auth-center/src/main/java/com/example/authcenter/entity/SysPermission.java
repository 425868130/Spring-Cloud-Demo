package com.example.authcenter.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * sys_permission
 *
 * @since 2020-07-30 15:17:15
 * @author xujw
 */
@Data
public class SysPermission implements Serializable {
    private Long id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 描述说明
     */
    private String description;

    /**
     * 权限状态定义
     */
    private Integer status;

    /**
     * 过期时间
     */
    private Date expireTime;

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