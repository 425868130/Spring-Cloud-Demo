package com.feign.provider.userService;

import com.example.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "user-service", path = "user", fallback = UserServiceFallBack.class)
public interface UserServiceFeign {
    @RequestMapping("info")
    Result getInfo();
}
