package com.example.authcenter;

import com.example.authcenter.service.tokenService.TokenService;
import com.example.common.define.ClientAgent;
import com.example.common.define.ConstVal;
import com.example.common.define.ShiroJWTAuthenticationToken;
import com.example.common.define.jwt.JwtPayload;
import com.example.common.define.jwt.UserPayload;
import com.example.common.util.jwt.JwtRs256Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.apache.shiro.SecurityUtils;
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
    @Value("${key-pair.auth-center.private}")
    private String privateKey;
    @Autowired
    TokenService tokenService;

    @Test
    public void createToken() {
        UserPayload jwtPayload = new UserPayload(1186854428227661825L);
        jwtPayload.setRequestIp("127.0.0.1")
                .setClientAgent(ClientAgent.PC_DESKTOP);
        System.out.println(JwtRs256Util.createJWT(privateKey, jwtPayload, ConstVal.Token.EXPIRES).orElse(""));
    }

    @Test
    public void shiroAuth() {
//        ShiroJWTAuthenticationToken jwtAuthenticationToken = new ShiroJWTAuthenticationToken(createToken());
//        SecurityUtils.getSubject().login(jwtAuthenticationToken);
    }

}
