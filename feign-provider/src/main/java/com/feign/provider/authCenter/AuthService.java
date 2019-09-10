package com.feign.provider.authCenter;

import com.example.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "auth-center", fallback = AuthServiceFallBack.class)
public interface AuthService {
    @RequestMapping("msg")
    Result msg();
}
