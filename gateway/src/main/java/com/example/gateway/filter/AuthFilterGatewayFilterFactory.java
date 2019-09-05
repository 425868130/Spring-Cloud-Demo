package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component("AuthFilter")
public class AuthFilterGatewayFilterFactory extends AbstractGatewayFilterFactory {
    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            String url = exchange.getRequest().getURI().getPath();
            System.out.println("自定义拦截器请求路径:" + url);
            return chain.filter(exchange);
        };
    }
}
