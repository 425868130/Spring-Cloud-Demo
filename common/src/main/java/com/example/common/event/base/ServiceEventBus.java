package com.example.common.event.base;

/**
 * 业务层事件总线接口,作为事件触发统一入口
 *
 * @author xujw
 * @since 2020-08-03 14:41:09
 */
public interface ServiceEventBus {
    /**
     * 触发事件
     *
     * @param event 被触发的事件对象
     */
    void publishEvent(ServiceEvent event);
}
