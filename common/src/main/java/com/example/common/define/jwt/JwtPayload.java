package com.example.common.define.jwt;

import com.example.common.define.ClientAgent;
import io.jsonwebtoken.Claims;

/**
 * @author xujw 2019-10-31 15:57:46
 * 封装一层业务层的jwt荷载接口,用于统一业务层jwt数据操作方式，以及自定义payload扩展
 */
public interface JwtPayload extends Claims {
    //请求token的ip地址
    String REQUEST_IP = "requestIp";
    /*请求token的客户端类型*/
    String CLIENT_AGENT = "clientAgent";

    default String getRequestIp() {
        return get(REQUEST_IP, String.class);
    }

    default JwtPayload setRequestIp(String requestIp) {
        put(REQUEST_IP, requestIp);
        return this;
    }

    default Integer getClientAgent() {
        return get(CLIENT_AGENT, Integer.class);
    }

    default JwtPayload setClientAgent(ClientAgent clientAgent) {
        put(CLIENT_AGENT, clientAgent.getCode());
        return this;
    }

}
