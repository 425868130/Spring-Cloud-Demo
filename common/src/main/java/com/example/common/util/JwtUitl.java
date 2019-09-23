package com.example.common.util;
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
 * @author: xujw 2018/10/9 21:14
 **/
public class JwtUitl {
    //秘钥
    private static String base64Secret = "ZTEzZWMyMGVhNmQ5MWY5YjcwOTE1NWQ2ZmIzYWUzMDA=";
    //token失效时间

    private static int expiresMs = 10000;

    private static String iss = "xjwcode.com";

    /**
     * 解析jwt
     *
     * @param jsonWebToken
     * @return
     */
    public static Claims parseJWT(String jsonWebToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            //未找到对应jwt信息
            return null;
        }
    }

    /**
     * 创建jwt
     *
     * @param params 参数列表
     * @return
     */
    public static String createJWT(Map<String, Object> params, String tokenId) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (tokenId == null) {
            tokenId = UUID.randomUUID().toString();
        }
        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setClaims(params)
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

    public static void main(String[] args) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("account", "425868130");
        userInfo.put("pswd", "1320074071");
        String token = createJWT(userInfo, "1111111");
        System.out.println(token);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String token2 = createJWT(userInfo, "1111111");
        String fakeToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1MzkwOTEyOTIsInBzd2QiOiIxMzIwMDc0MDcxIiwiaXNzIjoieGp3Y29kZS5jb20iLCJleHAiOjE1MzkwOTEzMDIsImlhdCI6MTUzOTA5MTI5MiwiYWNjb3VudCI6IjQyNTg2ODEzMCIsImp0aSI6IjY5NzczYjU5LWM5N2EtNGY5Ny1iYzk2LWMzYWIwZjQzMGEwMCJ9.7r_L7c3qRFqYje3U-f15rkuavtLPy7roS6R6Fq5unbU";
        System.out.println(token2);
       /* Claims tokenObj = parseJWT(token);

        tokenObj.setExpiration(new Date(System.currentTimeMillis() + 10000)).setNotBefore(new Date());
        String newToken = Jwts.builder().setClaims(tokenObj).compact();
        System.out.println(newToken);*/
    }
}
