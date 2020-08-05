package com.example.common.config;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountAuthCfg implements Serializable {
    private static final long serialVersionUID = 395404702454394650L;
    private String publicKey;
    private String privateKey;
}
