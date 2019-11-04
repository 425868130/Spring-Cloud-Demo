package com.example.common.define;

/**
 * @author xujw 2019-8-5 17:04:25
 * 统一前端返回数据对象
 */
public class Result<T> {
    private int status;
    private String msg;
    private T data;

    public Result() {
        this(StatusCode.SUCCESS);
    }

    public Result(StatusCode code) {
        status = code.getCode();
        msg = code.getDesc();
    }

    public Result(StatusCode code, T data) {
        this.data = data;
        status = code.getCode();
        msg = code.getDesc();
    }

    public Result(StatusCode code, String msg) {
        this.status = code.getCode();
        this.msg = msg;
    }

    /*快捷构建方法*/
    public static <T> Result<T> success() {
        return new Result<>();
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>().setData(data);
    }

    public static <T> Result<T> error(StatusCode code) {
        return new Result<>(code);
    }

    /* get set */
    public int getStatus() {
        return status;
    }

    public Result<T> setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }
}
