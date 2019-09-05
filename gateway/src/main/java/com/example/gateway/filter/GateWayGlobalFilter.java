package com.example.gateway.filter;

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
@Component
public class GateWayGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        System.out.println("请求地址:" + url);
        return chain.filter(exchange);
    }

    /*过滤器优先级设置，值越小优先级越高*/
    @Override
    public int getOrder() {
        return -999;
    }
}
