package com.example.common.define;

/**
 * @author xujw
 * 业务公共常量定义
 */
public interface ConstVal {
    //标准日期格式
    String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    interface Token {
        //token默认失效时间30分钟，单位毫秒
        int EXPIRES = 1800000;
    }

    interface RedisPrefix {
        String BLACKLIST = "TOKEN_BLACKLIST";
    }

}
