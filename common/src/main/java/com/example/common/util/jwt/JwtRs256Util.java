package com.example.common.util.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.RSAUtil;
import org.springframework.util.Base64Utils;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.util.*;

/**
 * 基于rs256加密方式的jwt工具类
 */
public class JwtRs256Util {

    //秘钥
    private static String base64Secret = "ZTEzZWMyMGVhNmQ5MWY5YjcwOTE1NWQ2ZmIzYWUzMDA=";
    //token失效时间

    private static int expiresMs = 10000;

    private static String iss = "xjwcode.com";

    public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.RS256;

    public static String createJWT(Map<String, Object> params, String privateKey, String tokenId) throws Exception {
        Date now = new Date();
        long expMillis = now.getTime() + expiresMs;
        Jwts.builder().setId(tokenId)
                .setIssuer(iss)
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", SIGNATURE_ALGORITHM.getValue())
                .setIssuedAt(now)
                .setClaims(params)
                .setExpiration(new Date(expMillis))
                .setNotBefore(now)
                .signWith(SignatureAlgorithm.RS256, getPKCS8PrivateKey(privateKey))
                .compact();
        return "";
    }
    private static PrivateKey getPKCS8PrivateKey(String strPk) throws Exception {
        // Remove markers and new line characters in private key
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(strPk.getBytes());
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }


    private static PrivateKey getPrivateKey(String strPk) throws Exception {
        // Remove markers and new line characters in private key
        String realPK = strPk.replaceAll("-----END RSA PRIVATE KEY-----","")
                .replaceAll("-----BEGIN RSA PRIVATE KEY-----", "")
                .replaceAll("\n","");
        byte[] b1 = Base64.getDecoder().decode(realPK);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b1);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }
    public static void main(String[] args) {
        String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
                "Proc-Type: 4,ENCRYPTED\n" +
                "DEK-Info: DES-EDE3-CBC,2DB10E20A88115D7\n" +
                "VrMncXtQTQwrtWCKXs+8xez1syLuuRGxipt+TCZk7lIh3JEPl8m9MJ4O/6J2ARg2\n" +
                "quvTjwflZxEB+L/OpuweOKZ+OVpRhmgFZZymQJjYQb/O2mSbdwIZunW/WZEaLM8K\n" +
                "LKsHPw5aVOhIilbCk6P9wuYI1cfHGEU01MYR/B4ksawQ6qprP1FfWcVY6Ymtn25Q\n" +
                "MwIdcWKjR6gRWHQYVdJRc6bRlNJZJR4CYMlqapEMOe7XinyMjgqPePCD1tR4VlMt\n" +
                "EHDY4yJeeHio0bZ2Pm+08LqYkH7huV//rorDslIOAwHr0H9NVlO9J2JWJ4pmCZNi\n" +
                "YFOsoLjHq7mNB0kvavmeyBmwuWiHqDa/nD1PbQOhbvmyQ263SIws0FkucD+xVC1a\n" +
                "KwnPvqVoeeEDl5gCPp1I7TWBHVsoDOoGnWqajZHFhvUrKFd7NPPHYwGRXCCe0KRw\n" +
                "xG+LxSVs8+1a8dko1DSZ+RaaqAQL05JSP+HtNLavKe1y3HYJ7zMAsJ8/xcqm1dx7\n" +
                "nykbGljZ1aZA89I3RWdGBVtnAOGbKFra7Vm1p0CGdzGe1cmkljm3DT/+0pARjOpq\n" +
                "RmTbMX5YosbUCJfQZeNw1OtoPIQ4a52QjUTikc9HlhF+BJ2piQ+t9Ac8xZi2xxAA\n" +
                "w7WoTVDhjP9ixeUcvasuKtN+3VijvIcnBAWSV3zFV+4fKfsoUWwT+v72KiAuqVC5\n" +
                "R9+hKbPq2YyR9nJM5cITAsixtn0a7mriebCbiR5KqesfHFJY/1rYoSRlkbRdmImp\n" +
                "zT13cSY3b9SqK9ZysbVo+NFzMTK3odAF+zDwMPB9eSvhRZ7RleIP9m3L3Qw+d/Lp\n" +
                "CC0kwfdkZ2e6042oIA7yHQmn4y4a+6TBLSi/3EpOd4wyE83lA7dR0pt36C7lkt3x\n" +
                "XRTLmK5vJuaCnKye4hMMNmLNaXbr9OGNPZx/8hmGJcR7ySZtorsGp6DU1RYDff1M\n" +
                "TMh2fiNcF91j5hSpfvCuPnIbskj0mvW+ZKdMAhbDe/dBbMdTmXzp6AFnCZZ30I6q\n" +
                "ajyoaLVY2y5te26+TEFfnjPoEwTEBet9eSJyw209CYXf7KlgvleCVyZ/3Ac5EHwL\n" +
                "iJ95d0WwGI8ZPYYHgTSNOpzsruUw5/pEEyw1/Ar/Q8O9GU4IUpsKjzhFN2sWR35W\n" +
                "NSvKLNI/BicjeneVv6lzIDwW0qhL0TVt9at5L8hRYEr3p9hlCg4vlqs400P+YRFr\n" +
                "aYbiQy8CW1V9nRY0geNjLAco3/6BPAyH3iApDgwtnxFHdJoCCKQWiaxq1Zc6vIy8\n" +
                "iDymZit0hUq7wJJ8mTW23ic57liuyEh06/Op8NSPY0OIlSOcr9sC9+RjOOrq+qhE\n" +
                "CaYeft+A0aw0HIqJVrrFQkxizNqDsY+vN5KKwZz3orrJVHv3jAz9rM5xItkG5Cv0\n" +
                "lAXqSA0olhrNzB7d3OCeSwqu6VCAdXVE+rpk1Ia3mtnD6VptHdzk9yuSjzAgYk9i\n" +
                "AUM8R/SnUAvp47FqWxPX8bW0083AJhpGLJaj8jJN+OPoNPfEvb9D6X7xcSLNB++B\n" +
                "ZTS/TZER/ptIUZ0/TQ7ra5eSlrGGMZnAVzz9B7PUHZ0kDA9YPQvtaw==\n" +
                "-----END RSA PRIVATE KEY-----";
        String tokenId = UUID.randomUUID().toString();

        Map<String, Object> map = new HashMap();
        map.put("user", "xujw");
        try {
            System.out.println(createJWT(map, privateKey, tokenId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
