package com.feign.provider.userService;

import com.example.common.define.StatusCode;
import com.example.common.define.Result;

public class UserServiceFallBack implements UserServiceFeign {
    @Override
    public Result getInfo() {
        return new Result(StatusCode.TIMEOUT);
    }
}
