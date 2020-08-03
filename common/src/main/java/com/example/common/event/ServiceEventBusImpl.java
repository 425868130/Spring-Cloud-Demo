package com.example.common.event;

import com.example.common.event.base.ServiceEvent;
import com.example.common.event.base.ServiceEventBus;
import com.example.common.event.base.EventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ServiceEventBusImpl implements ServiceEventBus {
    private final List<EventListener> listenerList;

    public ServiceEventBusImpl(List<EventListener> listenerList) {
        this.listenerList = listenerList;
    }

    private List<EventListener> getEventListener(ServiceEvent event) {
        List<EventListener> notifyList = new ArrayList<>();
        if (CollectionUtils.isEmpty(listenerList)) {
            return notifyList;
        }
        for (EventListener listener : listenerList) {
            if (listener.accept(event)) {
                notifyList.add(listener);
            }
        }
        return notifyList;
    }

    @Async
    @Override
    public void emit(ServiceEvent event) {
        if (event == null || event.mandatoryCheck()) {
            return;
        }
        List<EventListener> listeners = getEventListener(event);
        for (EventListener listener : listeners) {
            listener.handle(event);
        }
    }
}
