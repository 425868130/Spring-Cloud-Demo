package com.example.common.define;

import java.util.function.Supplier;

/**
 * Service层统一异常对象
 *
 * @author xujw 2019-8-5 18:03:32
 */
public class ServiceException extends RuntimeException implements Supplier<String> {
    private static final long serialVersionUID = 1931344449585521533L;
    private final StatusCode code;

    public ServiceException(StatusCode code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(StatusCode statusCode) {
        super(statusCode.getDesc());
        this.code = statusCode;
    }

    public static ServiceException withCode(StatusCode statusCode) {
        return new ServiceException(statusCode);
    }

    public static ServiceException withCode(StatusCode statusCode, String message) {
        return new ServiceException(statusCode, message);
    }

    public StatusCode getCode() {
        return code;
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public String get() {
        return getMessage();
    }
}
