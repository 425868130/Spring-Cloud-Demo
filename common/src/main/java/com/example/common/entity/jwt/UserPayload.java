package com.example.common.entity.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * @author xujw 2019-10-31 16:11:02
 * 自定义用户账户信息jwt荷载
 */
@Data
@NoArgsConstructor
public class UserPayload extends DefaultJwtPayload {

    public interface Key {
        String USER_ID = "userId";
        String USER_NAME = "userName";
        String REQUEST_IP = "userName";
        String CLIENT_AGENT = "clientAgent";
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

    public Optional<String> getUserName() {
        return Optional.ofNullable(get(Key.USER_NAME, String.class));
    }

    public UserPayload setUserName(String userName) {
        put(Key.USER_NAME, userName);
        return this;
    }
}
