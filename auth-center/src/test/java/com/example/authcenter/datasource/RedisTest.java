package com.example.authcenter.datasource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void addData() {
        for (int i = 0; i < 100; i++) {
            redisTemplate.opsForValue().set("key" + i, "value:" + i);
        }
    }

    @Test
    public void getData() {
        for (int i = 0; i < 100; i++) {
            System.out.println(redisTemplate.opsForValue().get("key" + i));
        }
    }
}
