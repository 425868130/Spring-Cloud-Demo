package com.example.authcenter.controller;

import com.example.common.entity.Result;
import com.feign.provider.userService.UserServiceFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RefreshScope
@RestController
public class AuthController {
    @Autowired
    UserServiceFeign userServiceFeign;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Value("${msg}")
    private String msg;

    @RequestMapping("msg")
    public Result auth() {
        return Result.success(msg);
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        if (principal != null) {
            logger.info(principal.toString());
        }
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        return principal;
    }

    @RequestMapping("user")
    public Result userInfo() {
        return userServiceFeign.getInfo();
    }
}
