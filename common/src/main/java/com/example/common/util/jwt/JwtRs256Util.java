package com.example.common.util.jwt;

import com.example.common.define.ConstVal;
import com.example.common.define.jwt.DefaultJwtPayload;
import com.example.common.define.jwt.JwtPayload;
import com.example.common.util.RSAUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * 基于rs256加密方式的jwt工具类
 */
@Slf4j
public class JwtRs256Util {

    private static String iss = "xjwcode.com";

    /**
     * 根据给定条件创建token
     *
     * @param privateKeyStr 用于签名的私钥字符串
     * @param payload       荷载内容对象,JwtPayload对象
     * @param duration      有效持续时间,单位毫秒
     * @return
     */
    public static Optional<String> createJWT(String privateKeyStr, JwtPayload payload, long duration) {
        String token = null;
        Date now = new Date();
        long expMillis = duration <= 0 ? ConstVal.Token.EXPIRES : now.getTime() + duration;
        try {
            if (payload.getId() == null) {
                payload.setId(UUID.randomUUID().toString());
            }
            if (StringUtils.isEmpty(payload.getIssuer())) {
                payload.setIssuer(iss);
            }
            token = Jwts.builder()
                    .setHeaderParam("typ", "JWT")
                    .setHeaderParam("alg", SignatureAlgorithm.RS256.getValue())
                    .setClaims(payload)
                    .setIssuedAt(now)
                    .setExpiration(new Date(expMillis))
                    .signWith(SignatureAlgorithm.RS256, RSAUtil.getPrivateKey(privateKeyStr))
                    .compact();
        } catch (Exception e) {
            log.error("token生成失败:" + e.getMessage());
        }
        return Optional.ofNullable(token);
    }

    public static Optional<JwtPayload> parseJWT(String jsonWebToken, String publicKeyStr) {
        if (jsonWebToken == null || publicKeyStr == null || "".equals(jsonWebToken)) {
            return Optional.empty();
        }
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(RSAUtil.getPublicKey(publicKeyStr))
                    .parseClaimsJws(jsonWebToken).getBody();
            return Optional.of(new DefaultJwtPayload(claims));
        } catch (Exception ex) {
            //未找到对应jwt信息
            return Optional.empty();
        }
    }
}
