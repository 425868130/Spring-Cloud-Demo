package com.feign.provider.userService;

import com.example.common.entity.Result;

public class UserServiceFallBack implements UserServiceFeign {
    @Override
    public Result getInfo() {
        return null;
    }
}
