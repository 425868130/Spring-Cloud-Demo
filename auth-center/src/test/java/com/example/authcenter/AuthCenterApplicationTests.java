package com.example.authcenter;

import com.example.authcenter.service.tokenService.TokenService;
import com.example.common.util.jwt.JwtRs256Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthCenterApplicationTests {
    @Value("${key-pair.auth-center.public}")
    private String publicKey;
    @Autowired
    TokenService tokenService;

    @Test
    public void contextLoads() {

    }

}
