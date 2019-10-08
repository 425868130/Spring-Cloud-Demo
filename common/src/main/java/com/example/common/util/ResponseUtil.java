package com.example.common.util;

import com.example.common.define.StatusCode;
import com.example.common.entity.Result;
import org.apache.commons.codec.Charsets;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.Nullable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

public class ResponseUtil {
    public static Mono<Void> result(@NotNull ServerHttpResponse response, @NotNull Result result,@Nullable HttpStatus status) {
        if (status != null) {
            response.setStatusCode(status);
        }
        byte[] bytes = JSON.stringify(result).getBytes(Charsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Flux.just(buffer));
    }
}
