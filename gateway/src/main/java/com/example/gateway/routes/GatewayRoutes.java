package com.example.gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class GatewayRoutes {
    /**
     * 使用java配置路由，也可以在properties中配置
     *
     * @param builder
     * @return
     */
//    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        p -> p.path("/get")
                                .filters(f -> f.addRequestHeader("Hello", "World"))
                                .uri("http://httpbin.org:80")
                )
                .route(
                        predicateSpec -> predicateSpec.path("/test/**")
                                .uri("lb://eureka-client01")
                )
                .build();
    }
}
