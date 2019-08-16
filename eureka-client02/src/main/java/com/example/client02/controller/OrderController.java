package com.example.client02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;
    /*
     * !!!
     * 这里的url写的是要调用的客户端在eureka中注册的名字，一般为对应配置的spring.application.name值
     * 不需要写端口号
     * */
    private static final String url = "http://eureka-client01";

    @RequestMapping("/getorder")
    public String getOrder() {
        // order 使用rpc 远程调用技术 调用 会员服务
        String memberUrl = url + "/getMember";
        String result = restTemplate.getForObject(memberUrl, String.class);
        System.out.println("会员服务调用订单服务,result:" + result);
        return result;
    }

    @RequestMapping("/getordermap")
    public Map<String, Object> getOrderMap() {
        // order 使用rpc 远程调用技术 调用 会员服务
        String memberUrl = url + "/getMap";
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(memberUrl, Map.class);
        Map<String, Object> map = new HashMap<String, Object>();
        map = responseEntity.getBody();
        System.out.println("niubi:" + map.get("age"));
        map.put("aa", "dsadsa");
        System.out.println("............:" + map);
        return map;
    }
}
