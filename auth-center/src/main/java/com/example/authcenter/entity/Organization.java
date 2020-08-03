package com.example.authcenter.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * organization
 *
 * @author xujw
 * @since 2020-5-23 11:01:00
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Organization implements Serializable {
    private static final long serialVersionUID = -1022848462668926030L;

    private Integer id;

    /**
     * 父组织id
     */
    private Integer parentId;

    /**
     * 组织名称
     */
    private String name;

    /**
     * 备注说明
     */
    private String description;

    /**
     * 组织logo图片地址
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