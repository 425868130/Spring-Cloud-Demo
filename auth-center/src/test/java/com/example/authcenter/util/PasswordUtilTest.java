package com.example.authcenter.util;

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
        System.out.println(passwordUtil.decrypt(""));
    }
}