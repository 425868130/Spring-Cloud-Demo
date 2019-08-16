package com.example.feign.controller;

import com.example.feign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//对本控制器的访问通过feign客户端调用其他模块完成，可以实现负载均衡，免去了RestTemplate调用
@RestController
public class ConsumerController {
    @Autowired
    FeignService feignService;

    //服务中方法的映射路径
    @RequestMapping("/client")
    public String client() {
        System.out.println("通过feign调用:");
        return feignService.client();
    }


    @RequestMapping("/getMember")
    public String getMember() {
        System.out.println("通过feign调用:");

        return feignService.getMember();
    }

    @RequestMapping("/getMap")
    public Map<Object, Object> getMap() {
        System.out.println("通过feign调用:");
        return feignService.getMap();
    }

}
