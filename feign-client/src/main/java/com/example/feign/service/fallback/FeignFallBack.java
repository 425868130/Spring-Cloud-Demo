package com.example.feign.service.fallback;

import com.example.feign.service.FeignService;

import java.util.Map;

/**
 * 服务降级处理，当调用失败时进行服务降级,
 * 改为调用本类中的方法
 */
public class FeignFallBack implements FeignService {
    @Override
    public String client() {
        return "client 方法降级";
    }

    @Override
    public String getMember() {
        return "getMember 方法降级";
    }

    @Override
    public Map<Object, Object> getMap() {
        System.out.println("getMap 方法降级");
        return null;
    }
}
