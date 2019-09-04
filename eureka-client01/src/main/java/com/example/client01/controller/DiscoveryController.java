package com.example.client01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DiscoveryController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String ip;
    @Value("${msg}")
    private String msg;

    @RequestMapping("/msg")
    public String msg() {
        return msg;
    }

    @GetMapping("/client")
    public String client() {
        String services = "Services: " + discoveryClient.getServices() + " ip :" + ip;

        System.out.println(services);
        return services;
    }

    @RequestMapping("/getMember")
    public String getMember() {
        return "this is getMember";
    }

    @RequestMapping("/getMap")
    public Map<Object, Object> getMap() {
        Map<Object, Object> map = new HashMap<>();
        map.put("age", 23);
        map.put("birth", "1996-09-05");
        return map;
    }
}
