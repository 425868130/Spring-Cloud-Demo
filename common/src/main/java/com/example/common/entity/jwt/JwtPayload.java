package com.example.common.entity.jwt;

import io.jsonwebtoken.Claims;

/**
 * @author xujw 2019-10-31 15:57:46
 * 封装一层业务层的jwt荷载接口,用于统一业务层jwt数据操作方式
 */
public interface JwtPayload extends Claims {
}
