package com.example.authcenter;

import com.example.common.CommonStartup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthCenterApplication extends CommonStartup {

    public static void main(String[] args) {
        SpringApplication.run(AuthCenterApplication.class, args);
    }

    @Override
    protected void init() {
        System.out.println("执行初始化操作");
    }
}
