package com.example.authcenter.dao;

import com.example.common.config.SystemCfg;
import com.example.common.util.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConfigTest {
    @Autowired
    private SystemCfg systemCfg;
    @Value("${system.security.account.privateKey}")
    private String privateKey;

    @Test
    public void test() {
        System.out.println(JSON.stringifyPretty(systemCfg));
        System.out.println("privateKey:" + privateKey);
    }
}
