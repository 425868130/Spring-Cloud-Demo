package com.example.common.event.base;

import com.example.common.event.define.EventAction;
import com.example.common.event.define.EventGroup;
import com.example.common.event.define.EventStatus;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 基础业务层事件对象,定义事件必须的字段属性,其他微服务模块根据需求扩展
 *
 * @author xujw
 * @since 2020-08-05 17:24:36
 */
@Data
@Accessors(chain = true)
public abstract class BaseServiceEvent implements ServiceEvent {
    private static final long serialVersionUID = 8035844664431218569L;

    private String eventId;
    private final EventGroup group;
    private final EventAction action;
    private EventStatus status;
    private String resultMsg;

    public BaseServiceEvent(EventGroup group, EventAction action, EventStatus status) {
        this.group = group;
        this.action = action;
        this.status = status;
    }
}
