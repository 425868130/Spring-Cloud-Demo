package com.example.authcenter.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * account_status
 *
 * @author xujw
 * @since 2020-07-30 15:53:56
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class AccountStatus implements Serializable {
    private static final long serialVersionUID = 1823914892344543331L;
    /**
     * 用户唯一标识
     */
    private Long uid;

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
}