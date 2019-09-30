package com.example.authcenter.service.tokenService;

import com.example.common.util.JSON;
import com.example.common.util.RSAUtil;
import com.example.common.util.jwt.JwtRs256Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RefreshScope
public class TokenServiceImpl implements TokenService {
    @Value("${key-pair.auth-center.private}")
    private String authPrivateKey;

    @Override
    public String generateToken() {
        String tokenId = UUID.randomUUID().toString();

        Map<String, Object> map = new HashMap();
        map.put("user", "xujw");
        try {
            String token = JwtRs256Util.createJWT(map, RSAUtil.getPrivateKey(authPrivateKey), tokenId);
            System.out.println("token: " + token);
            System.out.println("token内容：" + JSON.stringify(JwtRs256Util.parseJWT(token, RSAUtil.getPublicKey(authPrivateKey))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(authPrivateKey);
        return "";
    }
}
