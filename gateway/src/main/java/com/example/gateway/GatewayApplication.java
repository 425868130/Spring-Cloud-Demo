package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/*整合eureka实现微服务网关功能时，需要将gateway注册到服务中心*/
@RestController
@EnableEurekaClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @RequestMapping(value = "/fallbackcontroller")
    public Map<String, String> fallBackController() {
        Map<String, String> res = new HashMap();
        res.put("code", "-100");
        res.put("data", "service not available");
        return res;
    }
}
