package com.example.authcenter.controller;

import com.example.common.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthController {
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
}
