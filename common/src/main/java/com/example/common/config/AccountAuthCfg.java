package com.example.common.config;

import com.example.common.util.RSAUtil;
import lombok.Getter;
import org.springframework.util.StringUtils;

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
        if (StringUtils.isEmpty(publicKey)) {
            return;
        }
        this.publicKey = publicKey.replaceAll("\\s", "");
        this.publicKeyObj = RSAUtil.getPublicKey(this.publicKey);
    }

    public void setPrivateKey(String privateKey) {
        if (StringUtils.isEmpty(privateKey)) {
            return;
        }
        this.privateKey = privateKey.replaceAll("\\s", "");
        this.privateKeyObj = RSAUtil.getPrivateKey(this.privateKey);
    }
}
