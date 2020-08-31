package com.example.common.event.base;

import com.example.common.define.ServiceException;
import com.example.common.define.StatusCode;
import com.example.common.event.define.EventAction;
import com.example.common.event.define.EventGroup;
import com.example.common.event.define.EventStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.context.ApplicationEvent;

/**
 * 业务层事件对象包装
 */
@Getter
@Setter
@Accessors(chain = true)
public abstract class ServiceEvent extends ApplicationEvent {
    private static final long serialVersionUID = -2388635158259770079L;
    //获取事件id,由事件触发方生成,用于事件处理方识别
    private String eventId;
    //当前的事件组类型
    private EventGroup group;
    //事件动作类型
    private EventAction action;
    private EventStatus status;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public ServiceEvent() {
        super(new Object());
    }

    public ServiceEvent(EventGroup group, EventAction action, EventStatus status) {
        super(new Object());
        this.group = group;
        this.action = action;
        this.status = status;
    }

    public boolean mandatoryCheck() throws ServiceException {
        if (getGroup() == null || getAction() == null) {
            throw new ServiceException(StatusCode.PARAM_BLANK, "触发事件group/action参数不能为空");
        }
        if (getStatus() == null) {
            throw new ServiceException(StatusCode.PARAM_BLANK, "触发事件状态未初始化");
        }
        return true;
    }
}
