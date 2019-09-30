package com.example.authcenter.controller;

import com.example.authcenter.conf.JsonWebTokenKey;
import com.example.common.entity.Result;
import com.example.common.util.JSON;
import com.example.common.util.RSAUtil;
import com.example.common.util.jwt.JwtRs256Util;
import com.feign.provider.userService.UserServiceFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RefreshScope
@RestController
public class AuthController {
    @Autowired
    UserServiceFeign userServiceFeign;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Value("${msg}")
    private String msg;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String ip;
    @Value("${jwt.publicKey}")
    private String publicKey;
    @Value("${jwt.privateKey}")
    private String privateKey;

    @Autowired
    private JsonWebTokenKey jsonWebTokenKey;

    @GetMapping("/client")
    public Result client() {
        String services = "Services: " + discoveryClient.getServices() + " ip :" + ip;
        logger.info(services);
        return Result.success(services);
    }

    @RequestMapping("msg")
    public Result msg() {
        return Result.success(msg);
    }

    @RequestMapping("user")
    public Result userInfo() {
        return userServiceFeign.getInfo();
    }

    @RequestMapping("key")
    public Result getKey() {
        String tokenId = UUID.randomUUID().toString();

        Map<String, Object> map = new HashMap();
        map.put("user", "xujw");
        try {
            String token = JwtRs256Util.createJWT(map, RSAUtil.getPrivateKey(jsonWebTokenKey.getPrivateKey()), tokenId);
            System.out.println("token: " + token);
            System.out.println("token内容：" + JSON.stringify(JwtRs256Util.parseJWT(token, RSAUtil.getPublicKey(jsonWebTokenKey.getPublicKey()))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success(jsonWebTokenKey);
    }
}
