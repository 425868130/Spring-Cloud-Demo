package com.example.common.define;

/**
 * Service层统一异常对象
 *
 * @author xujw 2019-8-5 18:03:32
 */
public class ServiceException extends RuntimeException {
    private StatusCode code;

    public ServiceException(StatusCode code, String msg) {
        super(msg);
        this.code = code;
    }

    public StatusCode getCode() {
        return code;
    }
}
