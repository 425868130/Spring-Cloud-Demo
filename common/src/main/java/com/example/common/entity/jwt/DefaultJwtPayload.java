package com.example.common.entity.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.NoArgsConstructor;

/**
 * @author xujw 2019-10-31 16:09:58
 * 封装jwt的默认Claims作为业务层统一token荷载，同时作为自定义荷载的基类
 */
@NoArgsConstructor
public class DefaultJwtPayload extends DefaultClaims implements JwtPayload {
    public DefaultJwtPayload(Claims claims) {
        super(claims);
    }
}
