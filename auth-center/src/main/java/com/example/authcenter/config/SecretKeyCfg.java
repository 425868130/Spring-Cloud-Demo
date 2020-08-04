package com.example.authcenter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xujw
 * @since 2020-08-04 14:52:32
 */
@Component
@ConfigurationProperties(prefix = "ss")
public class SecretKeyCfg {
}
