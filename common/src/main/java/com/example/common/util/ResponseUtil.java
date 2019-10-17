package com.example.common.util;

import com.example.common.entity.Result;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.Nullable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

/**
 * Response工具类，方便使用ServerHttpResponse向页面返回json响应或进行跳转
 */
public class ResponseUtil {
    /**
     * 返回json数据
     *
     * @param response 响应对象
     * @param result   响应结果
     * @param status   响应状态码
     * @return
     */
    public static Mono<Void> result(@NotNull ServerHttpResponse response, @NotNull Result result, @Nullable HttpStatus status) {
        if (status != null) {
            response.setStatusCode(status);
        }
        byte[] bytes = JSON.stringify(result).getBytes();
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        /*必须指定编码，否则在浏览器中会中文乱码*/
        response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
        return response.writeWith(Flux.just(buffer));
    }

    /**
     * 进行页面跳转
     *
     * @param response 响应对象
     * @param url      要跳转的url地址
     * @return
     */
    public static Mono<Void> redirect(@NotNull ServerHttpResponse response, @NotNull String url) {
        //303状态码表示由于请求对应的资源存在着另一个URI，应使用GET方法定向获取请求的资源
        response.setStatusCode(HttpStatus.SEE_OTHER);
        response.getHeaders().set(HttpHeaders.LOCATION, url);
        return response.setComplete();
    }
}
