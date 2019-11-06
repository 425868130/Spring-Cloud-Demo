package com.example.authcenter.controller;

import com.example.common.define.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("token")
@RestController
public class TokenController {

    @PostMapping("refresh")
    public Result refreshToken() {
        return Result.success("refresh token");
    }
}
