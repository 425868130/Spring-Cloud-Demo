package com.example.common.event.base;

/**
 * 业务层事件监听器抽象接口
 *
 * @author xujw
 * @since 2020-08-03 14:31:25
 */
public interface EventListener {
    /**
     * 当前监听器是否接受并处理指定事件
     *
     * @param event 事件对象
     * @return 判断结果
     */
    boolean accept(ServiceEvent event);

    void handle(ServiceEvent event);
}
