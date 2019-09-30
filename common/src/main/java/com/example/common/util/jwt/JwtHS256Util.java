package com.example.common.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 基于HS256加密的token工具类
 *
 * @author: xujw 2018/10/9 21:14
 **/
public class JwtHS256Util {
    //token失效时间
    private static int expiresMs = 10000;

    private static String iss = "xjwcode.com";

    /**
     * 创建jwt
     *
     * @param claims 参数列表
     * @return
     */
    public static String createJWT(String tokenId, String secretKey, Map<String, Object> claims) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (tokenId == null) {
            tokenId = UUID.randomUUID().toString();
        }
        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", signatureAlgorithm.getValue())
                .setClaims(claims)
                //设置签发时间
                .setIssuedAt(now)
                .setIssuer(iss)
                .setId(tokenId)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (expiresMs >= 0) {
            long expMillis = nowMillis + expiresMs;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        //生成JWT
        return builder.compact();
    }

    /**
     * 解析jwt
     *
     * @param jsonWebToken
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String secretKey) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            //未找到对应jwt信息
            return null;
        }
    }


    public static void main(String[] args) {
        //秘钥,放到配置文件中
        String base64Secret = "ZTEzZWMyMGVhNmQ5MWY5YjcwOTE1NWQ2ZmIzYWUzMDA=";

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("account", "425868130");
        userInfo.put("pswd", "1320074071");
        String token = createJWT("1111111", base64Secret, userInfo);
        System.out.println(token);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Claims tokenObj = parseJWT(base64Secret, token);
        System.out.println();

        assert tokenObj != null;
        tokenObj.setExpiration(new Date(System.currentTimeMillis() + 10000)).setNotBefore(new Date());
        String newToken = Jwts.builder().setClaims(tokenObj).compact();
        System.out.println(newToken);
    }
}
