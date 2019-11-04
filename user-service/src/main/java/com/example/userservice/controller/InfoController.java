package com.example.userservice.controller;

import com.example.common.define.Result;
import com.example.userservice.bean.User;
import com.feign.provider.authCenter.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

//添加配置刷新支持
@RefreshScope
@RestController
@RequestMapping("user")
public class InfoController {
    private final static Logger logger = LoggerFactory.getLogger(InfoController.class);
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    AuthService authService;
    @Value("${server.port}")
    private String ip;
    @Autowired
    private DiscoveryClient discoveryClient;
    @GetMapping("/client")
    public Result client() {
        String services = "Services: " + discoveryClient.getServices() + " ip :" + ip;
        logger.info(services);
        return Result.success(services);
    }
    @RequestMapping("info")
    public Result getInfo() {
        User user = new User();
        user.setId(111111L);
        user.setAge(20);
        user.setName("info");
        logger.info("接口访问");
        return Result.success(user);
    }

    @RequestMapping("test")
    public Result test() {
        return authService.msg();
    }
}
