package com.example.common.event;

import com.example.common.event.base.ServiceEvent;
import com.example.common.event.base.ServiceEventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServiceEventBusImpl implements ServiceEventBus {
    private final ApplicationContext applicationContext;

    public ServiceEventBusImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void publishEvent(ServiceEvent applicationEvent) {
        if (applicationEvent == null) {
            return;
        }
        applicationContext.publishEvent(applicationEvent);
    }
}
