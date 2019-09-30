package com.feign.provider.authCenter;

import com.example.common.define.StatusCode;
import com.example.common.entity.Result;
import com.feign.provider.dto.UserAuthDTO;

public class AuthServiceFallBack implements AuthService {
    @Override
    public Result msg() {
        return new Result(StatusCode.TIMEOUT);
    }

    @Override
    public Result UserAuth(UserAuthDTO userAuthDTO) {
        return new Result(StatusCode.TIMEOUT);
    }
}
