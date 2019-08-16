package com.example.authcenter.controller;

import com.example.common.entity.Result;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    Result auth() {
        return new Result();
    }
}
