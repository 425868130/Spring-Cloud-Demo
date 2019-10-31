package com.example.common.entity;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author xujw 2019-10-24 15:28:30
 * 定义用于shiro鉴权的token类
 */
public class ShiroJWTAuthenticationToken implements AuthenticationToken {
    private static final long serialVersionUID = 8513061440977320446L;
    /*token字符串*/
    private String token;

    public ShiroJWTAuthenticationToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }


    @Override
    public Object getCredentials() {
        return token;
    }
}
