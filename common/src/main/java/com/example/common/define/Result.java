package com.example.common.define;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 统一前端返回数据对象
 *
 * @author xujw
 * @since 2019-8-5 17:04:25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Result {

    public final static Result OK = new Result(StatusCode.SUCCESS);

    private int status;
    private String msg;
    private Object data;

    public Result(StatusCode code) {
        status = code.getCode();
        msg = code.getDesc();
    }

    public Result(StatusCode code, Object data) {
        this.data = data;
        status = code.getCode();
        msg = code.getDesc();
    }

    public Result(StatusCode code, String msg) {
        this.status = code.getCode();
        this.msg = msg;
    }

    public static Result ok(Object data) {
        return new Result(StatusCode.SUCCESS, data);
    }

    public static Result error(StatusCode code) {
        return new Result(code);
    }
}
