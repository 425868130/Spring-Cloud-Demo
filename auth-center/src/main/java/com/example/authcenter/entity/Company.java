package com.example.authcenter.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * company
 *
 * @author xujw
 * @since 2020-5-23 11:01:00
 */
@Data
public class Company implements Serializable {
    private Integer id;

    /**
     * 所属组织id
     */
    private Integer organizationId;

    /**
     * 父公司/租户id
     */
    private Integer parentId;

    /**
     * 公司/租户名称
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

    private static final long serialVersionUID = 1L;
}