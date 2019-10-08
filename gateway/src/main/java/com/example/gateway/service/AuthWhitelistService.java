package com.example.gateway.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.util.List;

/**
 * 认证白名单判断
 *
 * @author xujw 2019-10-8 11:34:26
 */
@Service
@RefreshScope
public class AuthWhitelistService {
    private final static PathMatcher pathMatcher = new AntPathMatcher();
    
    @Value("${authWhitelist}")
    private List<String> authWhitelist;

    /**
     * 判断指定url是否在白名单中
     *
     * @param uri 请求地址的uri对象
     * @return
     */
    public boolean inWhitelist(URI uri) {
        if (uri == null || StringUtils.isEmpty(uri.getPath())) {
            return false;
        }
        String path = uri.getPath();
        return authWhitelist.stream().anyMatch(item -> pathMatcher.match(item, path));
    }
}
