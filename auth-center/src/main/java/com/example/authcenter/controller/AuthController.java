package com.example.authcenter.controller;

import com.example.common.entity.JsonWebTokenKey;
import com.example.common.entity.Result;
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

        return Result.success(jsonWebTokenKey.getPrivateKey());
    }
}
