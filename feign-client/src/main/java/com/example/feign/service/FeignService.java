package com.example.feign.service;

import com.example.feign.service.fallback.FeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/*声明Feign客户端同时指定要访问的服务名称,同时指定服务降级处理类*/
@FeignClient(value = "eureka-client01", fallback = FeignFallBack.class)
public interface FeignService {
    //服务中方法的映射路径
    @RequestMapping("/client")
    String client();

    @RequestMapping("/getMember")
    String getMember();

    @RequestMapping("/getMap")
    Map<Object, Object> getMap();

}
