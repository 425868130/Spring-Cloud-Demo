package com.feign.provider.authCenter;

import com.example.common.define.StatusCode;
import com.example.common.entity.Result;

public class AuthServiceFallBack implements AuthService {
    @Override
    public Result msg() {
        return new Result(StatusCode.TIMEOUT);
    }
}
