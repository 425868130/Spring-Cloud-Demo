package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*如果从jar包中扫描feign接口,除非jar包中的包名与当前相同否则需要配置basePackages,不然启动时会注入失败*/
@EnableFeignClients(basePackages = {"com.feign.provider"})
/*整合eureka实现微服务网关功能时，需要将gateway注册到服务中心*/
@EnableEurekaClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
