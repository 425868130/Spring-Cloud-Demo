package com.example.common.aop;

import com.example.common.define.Result;
import com.example.common.define.ServiceException;
import com.example.common.define.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * web层统一异常处理器
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class BaseExceptionHandler {
    /**
     * 系统异常统一返回400异常状态码,中断请求
     *
     * @param exception 系统异常
     * @return 返回错误信息
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result exceptionHandler(Exception exception) {
        log.error("[系统异常]:" + exception.toString());
        return new Result(StatusCode.UN_LOGIN).setMsg(exception.getMessage());
    }

    /**
     * 业务层异常统一返回200状态码及对应的业务异常状态码,由前端进行处理
     *
     * @param serviceException 业务层异常
     * @return 返回错误信息
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result ServiceExceptionHandler(ServiceException serviceException) {
        log.error("[业务异常]:" + serviceException.getMessage() + serviceException.getCode());
        return new Result().setMsg(serviceException.getMessage()).setStatus(serviceException.getCode().getCode());
    }
}
