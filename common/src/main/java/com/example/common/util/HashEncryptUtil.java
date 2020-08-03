package com.example.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.UUID;

/**
 * 哈希加密相关工具类
 *
 * @author xujw
 * @since 2020-08-03 11:37:17
 */
public class HashEncryptUtil {
    public static final String ALGORITHM_NAME = "md5"; // 基础散列算法
    public static final int HASH_ITERATIONS = 2; // 自定义散列次数

    /**
     * 获取随机加密盐值,32位uuid
     *
     * @return 加密盐值
     */
    public static String getSalt() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 对指定字符进行加盐加密
     *
     * @param encryptStr 要加密的的字符
     * @param salt       加密盐值
     * @return 加密后的密码
     */
    public static String encryptWithSalt(String encryptStr, String salt) {
        return new SimpleHash(ALGORITHM_NAME, encryptStr, ByteSource.Util.bytes(salt), HASH_ITERATIONS).toHex();
    }
}