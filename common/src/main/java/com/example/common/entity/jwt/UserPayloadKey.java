package com.example.common.entity.jwt;

import com.example.common.define.ClientAgent;

public enum UserPayloadKey {
    //账号id
    UserId("userId", Long.TYPE),
    //用户名
    NAME("name", String.class),
    //颁发token时的请求ip
    IP("ip", String.class),
    //请求token时的设备类型
    CLIENT_AGENT("clientAgent", ClientAgent.class);

    UserPayloadKey(String key, Class<?> type) {
        this.key = key;
        this.type = type;
    }

    private String key;
    private Class<?> type;

    public String getKey() {
        return key;
    }

    public Class<?> getType() {
        return type;
    }
}