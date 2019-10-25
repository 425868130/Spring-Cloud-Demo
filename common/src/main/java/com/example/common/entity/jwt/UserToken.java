package com.example.common.entity.jwt;

import io.jsonwebtoken.impl.DefaultClaims;

public class UserToken extends DefaultClaims {
    public UserToken() {
        super();
    }

    public UserToken setUserId(Long userId) {
        setValue(UserPayloadKey.UserId.getKey(), userId);
        return this;
    }
}
