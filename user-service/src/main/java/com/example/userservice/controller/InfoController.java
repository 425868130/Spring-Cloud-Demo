package com.example.userservice.controller;

import com.example.common.entity.Result;
import com.example.userservice.bean.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class InfoController {

    @PostMapping("info")
    public Result getInfo() {
        User user = new User();
        user.setId(111111L);
        user.setAge(20);
        user.setName("hello");
        return Result.success(user);
    }
}
