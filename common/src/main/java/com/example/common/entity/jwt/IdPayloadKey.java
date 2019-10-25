package com.example.common.entity.jwt;

public enum IdPayloadKey {
    ID("id", Long.class);
    private String key;
    private Class type;

    IdPayloadKey(String key, Class type) {
        this.key = key;
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public Class getType() {
        return type;
    }
}
