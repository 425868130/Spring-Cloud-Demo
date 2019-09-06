package com.example.authcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/*如果从jar包中扫描feign接口,除非jar包中的包名与当前相同否则需要配置basePackages,不然启动时会注入失败*/
@EnableFeignClients(basePackages = {"com.feign.provider"})
@EnableEurekaClient
@SpringBootApplication
@EnableAuthorizationServer
public class AuthCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthCenterApplication.class, args);
    }

}
