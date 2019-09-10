package com.example.userservice.controller;

import com.example.common.entity.Result;
import com.example.userservice.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
//添加配置刷新支持
@RefreshScope
@RestController
@RequestMapping("user")
public class InfoController {
    @Autowired
    HttpServletRequest httpServletRequest;
    @Value("${base}")
    String base;

    @RequestMapping("info")
    public Result getInfo() {
        User user = new User();
        user.setId(111111L);
        user.setAge(20);
        user.setName(base);
        return Result.success(user);
    }
}
