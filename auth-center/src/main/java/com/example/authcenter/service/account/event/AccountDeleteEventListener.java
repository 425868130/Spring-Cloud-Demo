package com.example.authcenter.service.account.event;

import com.example.common.event.base.EventListener;
import com.example.common.event.base.ServiceEvent;
import com.example.common.event.define.EventAction;
import com.example.common.event.define.EventGroup;

public interface AccountDeleteEventListener extends EventListener {
    default boolean accept(ServiceEvent event) {
        return event.getGroup() == EventGroup.ACCOUNT_INFO && event.getAction() == EventAction.DELETE;
    }
}
