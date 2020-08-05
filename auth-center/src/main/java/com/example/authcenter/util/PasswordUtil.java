package com.example.authcenter.util;

import com.example.common.config.SystemCfg;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {
    private final SystemCfg systemCfg;

    public PasswordUtil(SystemCfg systemCfg) {
        this.systemCfg = systemCfg;
    }
}
