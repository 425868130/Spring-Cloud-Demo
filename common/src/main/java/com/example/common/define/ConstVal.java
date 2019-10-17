package com.example.common.define;

/**
 * @author xujw
 * 业务公共常量定义
 */
public interface ConstVal {
    //标准日期格式
    String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";
    //token默认失效时间30分钟，单位毫秒
    int TOKEN_EXPIRES = 1800000;

    interface RedisKey {
        String BLACKLIST = "TOKEN_BLACKLIST";
    }

}
