package com.example.common.define;

import java.io.Serializable;

/**
 * Api网关请求对象,网关对内部服务发起的请求都应继承该对象,该对象携带网关签名的token,
 * 用于授权中心根据该token鉴别是否为外部访问,没有该token的将被视为外部请求授权中心拒绝颁发token
 *
 * @author xujw 2019-9-30 17:58:45
 */
public class GatewayRequest implements Serializable {
    private static final long serialVersionUID = 523568961574943876L;
    private String gatewayToken;

    public String getGatewayToken() {
        return gatewayToken;
    }

    public void setGatewayToken(String gatewayToken) {
        this.gatewayToken = gatewayToken;
    }
}
