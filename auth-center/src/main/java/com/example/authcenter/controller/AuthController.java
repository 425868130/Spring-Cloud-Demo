package com.example.authcenter.controller;

import com.example.common.entity.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Value("${msg}")
    private String msg;

    @RequestMapping("msg")
    public Result auth() {
        return Result.success(msg);
    }
}
