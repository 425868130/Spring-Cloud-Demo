package com.example.authcenter.service.tokenService;

import com.example.common.define.ConstVal;
import com.example.common.util.jwt.JwtRs256Util;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RefreshScope
public class TokenServiceImpl implements TokenService {
    @Value("${key-pair.auth-center.private}")
    private String authPrivateKey;
    @Value("${key-pair.auth-center.public}")
    private String authPublicKey;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Optional<String> generateToken(Claims claims) {
        return JwtRs256Util.createJWT(authPrivateKey, claims, ConstVal.Token.EXPIRES);
    }

    @Override
    public Optional<Claims> parseJWT(String tokenStr) {
        return JwtRs256Util.parseJWT(tokenStr, authPublicKey);
    }

    @Override
    public void inspectToken(String tokenStr, Claims claims) {
        if (StringUtils.isEmpty(tokenStr) || claims == null) {
            return;
        }
        /*计算token剩余有效时间作为redis存储的有效时间*/
        Date exp = claims.getExpiration();
        long expTime = exp.getTime();
        long now = System.currentTimeMillis();
        if (now >= expTime) {
            /*当前时间大于过期时间则已过期,不进入黑名单*/
            return;
        }
        ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
        Object blackToken = valueOps.get(claims.getId());
        /*如果已经存在黑名单中则不重复添加*/
        if (blackToken != null) {
            return;
        }
        long timeOut = expTime - now + 30000; //计算剩余时间毫秒数，额外增加30s延时
        valueOps.set(claims.getId(), tokenStr);
        redisTemplate.expire(claims.getId(), timeOut, TimeUnit.MILLISECONDS);

    }
}
