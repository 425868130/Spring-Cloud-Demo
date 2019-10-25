package com.example.common.define;

/**
 * @author xujw 2019-10-25 11:48:17
 * 客户端类型枚举
 */
public enum ClientAgent {
    PC_WEB(1),              //pc网页端
    PC_DESKTOP(2),          //pc原生桌面端
    WEB_IOS(4),             //ios网页端
    WEB_ANDROID(5),         //安卓网页端
    APP_IOS(6),             //ios应用端
    APP_ANDROID(7),         //安卓应用端
    MICRO_APP_IOS(8),       //ios小程序端
    MICRO_APP_ANDROID(9),   //安卓小程序端
    ;

    private int agentCode;

    ClientAgent(int agentCode) {
        this.agentCode = agentCode;
    }

    public int getAgentCode() {
        return agentCode;
    }
}
