package com.example.authcenter.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * group
 *
 * @author xujw
 * @since 2020-5-23 11:01:00
 */
@Data
public class Group implements Serializable {
    private static final long serialVersionUID = -8717266412636806029L;

    private Long id;

    /**
     * 所属部门id
     */
    private Integer departmentId;

    /**
     * 小组名称
     */
    private String name;

    /**
     * 备注说明
     */
    private String description;

    /**
     * logo图片地址
     */
    private String logo;

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