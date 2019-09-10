package com.example.userservice.controller;

import com.example.common.entity.Result;
import com.example.userservice.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user")
public class InfoController {
    @Autowired
    HttpServletRequest httpServletRequest;


    @RequestMapping("info")
    public Result getInfo() {
        User user = new User();
        user.setId(111111L);
        user.setAge(20);
        user.setName("hello");
        return Result.success(user);
    }
}
