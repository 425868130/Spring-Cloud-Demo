package com.example.common.config;

import com.example.common.util.RSAUtil;
import lombok.Getter;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;

@Getter
public class AccountAuthCfg implements Serializable {
    private static final long serialVersionUID = 395404702454394650L;
    private String publicKey;
    private String privateKey;

    //公私钥对项
    private PrivateKey privateKeyObj;
    private PublicKey publicKeyObj;


    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
        this.publicKeyObj = RSAUtil.getPublicKey(publicKey);
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
        this.privateKeyObj = RSAUtil.getPrivateKey(privateKey);
    }
}
