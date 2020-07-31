package com.example.authcenter.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * department_user_relation
 *
 * @author xujw
 * @since 2020-5-23 11:01:00
 */
@Data
public class DepartmentUserRelation implements Serializable {
    private static final long serialVersionUID = 7495763994643222036L;
    /**
     * 用户id主键
     */
    private Long uid;

    /**
     * 部门id
     */
    private Long departmentId;

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