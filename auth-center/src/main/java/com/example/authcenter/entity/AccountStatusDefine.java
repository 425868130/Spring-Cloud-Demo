package com.example.authcenter.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * user_status_define
 *
 * @author xujw
 * @since 2020-5-23 11:07:02
 */
@Data
public class AccountStatusDefine implements Serializable {
    private static final long serialVersionUID = -1221161658556909581L;
    /**
     * 状态id
     */
    private Integer id;

    /**
     * 状态名称
     */
    private String name;

    /**
     * 备注说明
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}