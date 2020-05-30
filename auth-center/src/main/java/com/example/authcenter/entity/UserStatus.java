package com.example.authcenter.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * user_status
 *
 * @author xujw
 * @since 2020-5-23 11:06:51
 */
@Data
public class UserStatus implements Serializable {
    /**
     * 用户唯一标识
     */
    private String uid;

    /**
     * uid自定义状态,关联user_status表id
     */
    private Integer statusId;

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