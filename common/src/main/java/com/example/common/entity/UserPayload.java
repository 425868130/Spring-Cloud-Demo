package com.example.common.entity;

import io.jsonwebtoken.impl.DefaultClaims;

/**
 * @author xujw 2019-10-25 10:18:414
 * 包装jwt的Claims,规范token中保存的数据
 */
public class UserPayload extends DefaultClaims {

    public UserPayload() {
        super();
    }

}
