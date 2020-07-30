package com.example.authcenter.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * account_info
 *
 * @author xujw
 * @since 2020-07-30 15:36:55
 */
@Data
public class AccountInfo implements Serializable {
    private static final long serialVersionUID = 3070187599550381705L;
    /**
     * 用户唯一标识
     */
    private Long uid;

    /**
     * 账户名
     */
    private String accountName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 用户头像
     */
    private String headImg;

    /**
     * 所属地域
     */
    private String location;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 状态id
     */
    private Integer statusId;

    /**
     * 是否删除 1是 0 否
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除时间，只在deleted字段为1时有意义
     */
    private Date deleteTime;

}