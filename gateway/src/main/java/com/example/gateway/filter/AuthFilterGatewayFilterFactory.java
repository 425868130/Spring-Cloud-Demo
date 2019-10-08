package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义过滤器,命名规则： 名称+GatewayFilterFactory
 * 在gateway配置文件中使用名称部分即可,请求会先走全局过滤器再走自定义过滤器
 */
@Component
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
