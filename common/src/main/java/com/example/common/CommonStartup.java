package com.example.common;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;

/**
 * 通用公共启动类,其他微服务模块启动入口继承该类已实现自动扫描common包下的组建
 *
 * @author xujw
 * @since 2020-08-05 17:15:35
 */
@EnableAsync
@EnableEurekaClient
/*如果从jar包中扫描feign接口,除非jar包中的包名与当前相同否则需要配置basePackages,不然启动时会注入失败*/
@EnableFeignClients(basePackages = "com.feign.provider")
@SpringBootApplication
public abstract class CommonStartup {

    @PostConstruct
    void postConstruct() {
        init();
    }

    /**
     * 用于执行每个微服务模块启动前的配置初始化相关操作逻辑
     */
    protected abstract void init();
}
