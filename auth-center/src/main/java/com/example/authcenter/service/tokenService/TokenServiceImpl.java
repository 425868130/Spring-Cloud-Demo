package com.example.authcenter.service.tokenService;

import com.example.common.define.ConstVal;
import com.example.common.util.jwt.JwtRs256Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RefreshScope
public class TokenServiceImpl implements TokenService {
    @Value("${key-pair.auth-center.private}")
    private String authPrivateKey;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String generateToken() {
        Claims claims = new DefaultClaims();
        claims.put("user", "xujw");
        try {
            String token = JwtRs256Util.createJWT(authPrivateKey, claims, ConstVal.TOKEN_EXPIRES);
            System.out.println("token: " + token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void invalidToken(String tokenId, String tokenStr) {
        redisTemplate.opsForValue().set(tokenId, tokenStr);
        redisTemplate.expire(tokenId, 1000, TimeUnit.MILLISECONDS);

    }
}
