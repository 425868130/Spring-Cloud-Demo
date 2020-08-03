package com.example.common.event.base;

import com.example.common.event.define.EventAction;
import com.example.common.event.define.EventGroup;
import com.example.common.event.define.EventStatus;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public abstract class BaseServiceEvent implements ServiceEvent {
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
