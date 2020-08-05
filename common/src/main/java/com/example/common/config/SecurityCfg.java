package com.example.common.config;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xujw
 * @since 2020-08-04 14:52:32
 */
@Data
public class SecurityCfg implements Serializable {
    private static final long serialVersionUID = 3411302122676764455L;
    private AccountAuthCfg account;
    private ServiceAuthCfg service;
}
