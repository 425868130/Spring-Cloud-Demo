package com.example.authcenter.service.account.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 账号信息业务对象
 *
 * @author xujw
 * @since 2020-08-03 15:53:26
 */
@Data
@Accessors(chain = true)
public class AccountInfoBo implements Serializable {
    private static final long serialVersionUID = -2311756274141312778L;

    private Long uid;

    /**
     * 账户名,用于登录识别
     */
    private String account;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户真实姓名
     */
    private String realName;

    /**
     * 性别:1男0女-1未知
     */
    private Byte sex;

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
