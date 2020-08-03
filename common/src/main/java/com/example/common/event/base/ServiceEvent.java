package com.example.common.event.base;

import com.example.common.define.ServiceException;
import com.example.common.define.StatusCode;
import com.example.common.event.define.EventAction;
import com.example.common.event.define.EventGroup;
import com.example.common.event.define.EventStatus;

/**
 * 业务层事件,一个事件必须属于唯一一个事件组下的某个事件动作
 *
 * @author xujw
 * @since 2020-08-03 14:25:52
 */
public interface ServiceEvent {
    /**
     * 获取当前的事件组类型
     *
     * @return 事件组类型对应枚举类
     */
    EventGroup getGroup();

    /**
     * 获取事件动作类型
     *
     * @return 事件动作类型
     */
    EventAction getAction();

    /**
     * 获取事件编码,事件编码默认由事件组名-事件动作组合而成
     *
     * @return 事件编码值
     */
    default String getEventCode() {
        return getGroup() + "-" + getAction();
    }

    /**
     * 获取事件id,由事件触发方生成,用于事件处理方识别
     *
     * @return 事件id
     */
    String getEventId();

    /**
     * 获取当前事件的处理状态
     *
     * @return 状态枚举对象
     */
    EventStatus getStatus();

    /**
     * 获取当前事件处理结果信息，通常在处理失败时返回异常信息
     *
     * @return 处理结果信息字符串
     */
    default String getResultMsg() {
        return "";
    }

    default boolean mandatoryCheck() throws ServiceException {
        if (getGroup() == null || getAction() == null) {
            throw new ServiceException(StatusCode.PARAM_BLANK, "触发事件group/action参数不能为空");
        }
        if (getStatus() == null) {
            throw new ServiceException(StatusCode.PARAM_BLANK, "触发事件状态未初始化");
        }
        return true;
    }
}
