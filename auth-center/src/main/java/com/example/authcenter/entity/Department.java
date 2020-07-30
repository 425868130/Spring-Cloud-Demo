package com.example.authcenter.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * department
 *
 * @author xujw
 * @since 2020-5-23 11:01:00
 */
@Data
public class Department implements Serializable {
    private static final long serialVersionUID = -4311673062272297538L;

    private Integer id;

    /**
     * 所属组织id
     */
    private Integer organizationId;

    /**
     * 所属公司id
     */
    private Integer companyId;

    /**
     * 父部门id
     */
    private Integer parentId;

    /**
     * 部门名称
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