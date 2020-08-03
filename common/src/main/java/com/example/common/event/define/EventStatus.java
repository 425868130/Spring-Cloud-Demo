package com.example.common.event.define;

/**
 * 业务层事件状态信息枚举
 *
 * @author xujw
 * @since 2020-08-03 14:34:36
 */
public enum EventStatus {
    //已触发
    EMIT,
    //处理中
    PROCESSING,
    //处理成功
    SUCCESS,
    //处理失败
    FAILED,
    ;
}
