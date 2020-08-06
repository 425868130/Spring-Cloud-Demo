package com.example.common.component;

import com.example.common.config.AccountAuthCfg;
import com.example.common.config.SystemCfg;
import com.example.common.util.RSAUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

@Component
public class PasswordUtil {
    private final AccountAuthCfg accountAuthCfg;

    public PasswordUtil(SystemCfg systemCfg) {
        this.accountAuthCfg = systemCfg.getSecurity().getAccount();
    }

    /**
     * 加密密码并将加密结果使用base64处理
     *
     * @param originPassword 原始密码明文
     * @return 加密后的密码字符串
     */
    public String encrypt(String originPassword) {
        if (StringUtils.isEmpty(originPassword)) {
            return "";
        }
        return Base64Utils.encodeToString(RSAUtil.encrypt(originPassword.getBytes(), accountAuthCfg.getPublicKeyObj()));
    }

    /**
     * 解密密码信息
     *
     * @param passwordWithBase64 传入的密码应该是先被RSAUtil加密过并经过base64处理后的字符串
     * @return 解密后的密码原始字符串
     */
    public String decrypt(String passwordWithBase64) {
        if (StringUtils.isEmpty(passwordWithBase64)) {
            return "";
        }
        return new String(RSAUtil.decrypt(Base64Utils.decode(passwordWithBase64.getBytes()), accountAuthCfg.getPrivateKeyObj()));
    }

}
