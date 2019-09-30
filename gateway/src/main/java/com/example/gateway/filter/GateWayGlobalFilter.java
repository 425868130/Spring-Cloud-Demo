package com.example.gateway.filter;

import com.example.common.entity.Result;
import com.example.common.util.JSON;
import com.feign.provider.authCenter.AuthService;
import com.feign.provider.dto.UserAuthDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author xujw
 * 实现GlobalFilter作为全局过滤器,全部的请求都会进过该过滤器,无需配置
 */
@Slf4j
@Component
public class GateWayGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private AuthService authService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        System.out.println("请求地址:" + url);
        /*模拟请求从授权中心获取token*/
        Result result = authService.UserAuth(new UserAuthDTO("xujw", "1320074071"));
        log.info("token: " + JSON.stringify(result.getData()));
        return chain.filter(exchange);
    }

    /*过滤器优先级设置，值越小优先级越高*/
    @Override
    public int getOrder() {
        return -999;
    }
}
