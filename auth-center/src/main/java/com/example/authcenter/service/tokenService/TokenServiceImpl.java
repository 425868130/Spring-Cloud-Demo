package com.example.authcenter.service.tokenService;

import com.example.common.config.SystemCfg;
import com.example.common.define.ConstVal;
import com.example.common.define.jwt.JwtPayload;
import com.example.common.util.jwt.JwtRs256Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final String authPrivateKey;
    private final String authPublicKey;
    @Value("${system.security.account.privateKey}")
    private String privateKey;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public TokenServiceImpl(SystemCfg systemCfg) {
        this.authPrivateKey = systemCfg.getSecurity().getAccount().getPrivateKey();
        this.authPublicKey = systemCfg.getSecurity().getAccount().getPublicKey();
        System.out.println("privateKey:" + privateKey);
    }

    @Override
    public Optional<String> generateToken(JwtPayload jwtPayload) {
        return JwtRs256Util.createJWT(authPrivateKey, jwtPayload, ConstVal.Token.EXPIRES);
    }

    @Override
    public Optional<JwtPayload> parseJWT(String tokenStr) {
        return JwtRs256Util.parseJWT(tokenStr, authPublicKey);
    }

    /**
     * 将token添加到黑名单中
     *
     * @param tokenStr
     * @param jwtPayload
     */
    @Override
    public void inspectToken(String tokenStr, JwtPayload jwtPayload) {
        if (StringUtils.isEmpty(tokenStr) || jwtPayload == null) {
            return;
        }
        /*计算token剩余有效时间作为redis存储的有效时间*/
        Date exp = jwtPayload.getExpiration();
        long expTime = exp.getTime();
        long now = System.currentTimeMillis();
        if (now >= expTime) {
            /*当前时间大于过期时间则已过期,不进入黑名单*/
            return;
        }
        ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
        Object blackToken = valueOps.get(jwtPayload.getId());
        /*如果已经存在黑名单中则不重复添加*/
        if (blackToken != null) {
            return;
        }
        long timeOut = expTime - now + 30000; //计算剩余时间毫秒数，额外增加30s延时
        valueOps.set(jwtPayload.getId(), tokenStr);
        redisTemplate.expire(jwtPayload.getId(), timeOut, TimeUnit.MILLISECONDS);
    }

    /**
     * 判断指定token是否在黑名单中
     *
     * @param tokenStr
     * @return
     */
    @Override
    public boolean inBlackList(String tokenStr) {
        Optional<JwtPayload> payload = JwtRs256Util.parseJWT(tokenStr, authPublicKey);
        return inBlackList(payload.orElse(null));
    }

    /**
     * 判断指定token是否在黑名单中
     *
     * @param payload
     * @return
     */
    @Override
    public boolean inBlackList(JwtPayload payload) {
        if (payload == null) {
            return false;
        }
        return redisTemplate.opsForValue().get(payload.getId()) != null;
    }
}
