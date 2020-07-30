package com.example.common.define;

/**
 * 系统全局统一响应状态码
 *
 * @author xujw
 * @since 2019-8-5 17:54:12
 */
public enum StatusCode {
    SUCCESS(1000, "请求成功"),
    UN_LOGIN(2000, "用户未登录"),
    PERMISSION_DENIED(3000, "权限不足拒绝访问"),
    REQUEST_FAILED(4000, "请求失败"),
    REPEAT(5000, "重复操作"),
    TIMEOUT(6000, "请求超时"),
    SYSTEM_ERROR(7000, "系统异常"),
    /* 成功状态码 */
    /* 参数错误：10001-19999 */
    PARAM_INVALID(10001, "参数无效"),
    PARAM_BLANK(10002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),

    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录"),
    USER_LOGIN_ERROR(20002, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
    USER_NOT_EXIST(20004, "用户不存在"),
    USER_HAS_EXISTED(20005, "用户已存在"),

    /* 业务错误：30001-39999 */
    SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "某业务出现问题"),

    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),

    /* 数据错误：50001-599999 */
    RESULE_DATA_NONE(50001, "数据未找到"),
    DATA_IS_WRONG(50002, "数据有误"),
    DATA_ALREADY_EXISTED(50003, "数据已存在"),

    /* 接口错误：60001-69999 */
    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
    INTERFACE_OUTTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),

    /* 权限错误：70001-79999 */
    TOKEN_INVALID(70001, "token不存在或已失效"),
    PERMISSION_NO_ACCESS(70002, "无访问权限");


    private int code;
    private String desc;

    StatusCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public StatusCode setCode(int code) {
        this.code = code;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public StatusCode setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
