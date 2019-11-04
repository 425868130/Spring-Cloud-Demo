package com.feign.provider.authCenter;

import com.example.common.define.Result;
import com.feign.provider.dto.UserAuthDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "auth-center", fallback = AuthServiceFallBack.class)
public interface AuthService {
    @RequestMapping("msg")
    Result msg();

    @PostMapping("userAuth")
    Result UserAuth(UserAuthDTO userAuthDTO);
}
