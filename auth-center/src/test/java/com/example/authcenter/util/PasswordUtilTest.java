package com.example.authcenter.util;

import com.example.common.component.PasswordUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PasswordUtilTest {
    @Autowired
    private PasswordUtil passwordUtil;

    @Test
    void encrypt() {
        System.out.println(passwordUtil.encrypt("1320074071"));
    }

    @Test
    void decrypt() {
        String password = "bqHVpOaGuDiIThLg9ahSY+8StDYD2/Z7JrvNZKsC8O15WuPGiAjAqO5WKTM+D7Qt73zkuYfJTBYlsk2sarPirYl2iE9dtcYDN5QfwXYCc86ke3EzLWF71qtfaEHCm6yv0gVug9kxKhZG19Ed4R6CDpAaZn9YGbEuGKYuBubA7Eaz0aX1/OmmpSnpqgMKq9kEyr3ApqhBBeesGdSPxyWQhc0LwMblJx/yfu1mJ7XwDps6POlCgX2J+JlLJ9kml7yl+V3HNUD9NYjuqbgBS8P6AxSGd2eONbmLwBwDQGVyUxNfU/qDXHg15CbiOGDsFQBrxFpi+SQ7oiiN4t4aUkTTng==";
        System.out.println(passwordUtil.decrypt(password));
    }
}