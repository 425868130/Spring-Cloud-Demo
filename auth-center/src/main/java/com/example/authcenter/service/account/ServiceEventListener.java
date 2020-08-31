package com.example.authcenter.service.account;

import com.example.authcenter.service.account.event.AccountInfoEvent;
import com.example.common.event.base.ServiceEvent;
import com.example.common.util.JSON;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ServiceEventListener {

    @EventListener(AccountInfoEvent.class)
    public void onAccountCreate(ServiceEvent serviceEvent) {
        System.out.println("监听到事件:" + JSON.stringifyPretty(serviceEvent));
    }
}
