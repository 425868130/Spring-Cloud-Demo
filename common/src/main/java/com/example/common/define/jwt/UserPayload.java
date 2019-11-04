package com.example.common.define.jwt;

import java.util.Optional;

/**
 * @author xujw 2019-10-31 16:11:02
 * 自定义用户账户信息jwt荷载
 */
public class UserPayload extends DefaultJwtPayload {
    /*当前payload自定义扩充的属性，需要同时提供针对该属性的get set方法*/
    public interface Key {
        String USER_ID = "userId";
    }

    public UserPayload(long userId) {
        setUserId(userId);
    }

    public UserPayload(JwtPayload payload) {
        super(payload);
    }

    public Optional<Long> getUserId() {
        return Optional.ofNullable(get(Key.USER_ID, Long.class));
    }

    public UserPayload setUserId(long userId) {
        put(Key.USER_ID, userId);
        return this;
    }
}
