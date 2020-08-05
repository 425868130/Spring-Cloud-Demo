package com.example.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 系统级配置文件信息对象
 *
 * @author xujw
 * @since 2020-08-05 11:25:32
 */
@Data
@Component
@ConfigurationProperties(prefix = "system")
public class SystemCfg implements Serializable {
    private static final long serialVersionUID = -4786689684817965684L;

    private SecurityCfg security;
}
