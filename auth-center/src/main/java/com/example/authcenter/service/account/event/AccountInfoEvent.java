package com.example.authcenter.service.account.event;

import com.example.authcenter.entity.AccountInfo;
import com.example.common.event.base.BaseServiceEvent;
import com.example.common.event.define.EventAction;
import com.example.common.event.define.EventGroup;
import com.example.common.event.define.EventStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 账号信息相关事件对象,扩展自基础业务事件,增加账号相关信息
 */
@Getter
@Setter
@Accessors(chain = true)
public class AccountInfoEvent extends BaseServiceEvent {
    private static final long serialVersionUID = 3998731585153871437L;
    //额外扩展信息
    private String account;
    private String headImg;
    private Date createTime;

    public AccountInfoEvent(EventGroup group, EventAction action, EventStatus status, long eventId) {
        super(group, action, status);
        setEventId(String.valueOf(eventId));
    }

    public static AccountInfoEvent onAdd(AccountInfo accountInfo) {
        AccountInfoEvent accountInfoEvent = new AccountInfoEvent(EventGroup.ACCOUNT_INFO, EventAction.ADD, EventStatus.EMIT, accountInfo.getUid());
        accountInfoEvent.setAccount(accountInfo.getAccount());
        accountInfoEvent.setHeadImg(accountInfo.getHeadImg());
        accountInfoEvent.setCreateTime(accountInfo.getCreateTime());
        return accountInfoEvent;
    }

    public static AccountInfoEvent onDelete(long uid) {
        return new AccountInfoEvent(EventGroup.ACCOUNT_INFO, EventAction.DELETE, EventStatus.EMIT, uid);
    }

    public static AccountInfoEvent onUpdate(AccountInfo accountInfo) {
        AccountInfoEvent accountInfoEvent = new AccountInfoEvent(EventGroup.ACCOUNT_INFO, EventAction.UPDATE, EventStatus.EMIT, accountInfo.getUid());
        accountInfoEvent.setAccount(accountInfo.getAccount());
        accountInfoEvent.setHeadImg(accountInfo.getHeadImg());
        accountInfoEvent.setCreateTime(accountInfo.getCreateTime());
        return accountInfoEvent;
    }
}
