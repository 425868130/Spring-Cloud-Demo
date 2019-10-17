package com.example.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.util.List;

/**
 * 网关白名单配置,用于指定不需要token校验的路径或其他例外情况
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "whitelist")
public class WhitelistConfig {
    private final static PathMatcher pathMatcher = new AntPathMatcher();

    private List<String> token;

    /**
     * 判断指定url是否在token白名单中,在该token白名单的请求可以不携带验证token即可访问
     *
     * @param uri 请求地址的uri对象
     * @return
     */
    public boolean inTokenWhitelist(URI uri) {
        if (uri == null || StringUtils.isEmpty(uri.getPath())) {
            return false;
        }
        String path = uri.getPath();
        return token.stream().anyMatch(item -> pathMatcher.match(item, path));
    }
}