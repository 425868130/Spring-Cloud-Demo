package com.example.common.entity;

import com.example.common.interfaces.Token;
import lombok.Data;

import java.util.HashMap;

/**
 * @author xujw 2019-10-8 10:06:26
 * 定义jwtoken信息对象
 */
@Data
public class JWToken extends HashMap<String, Object> implements Token {
}
