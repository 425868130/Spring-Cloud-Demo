package com.example.authcenter.config;

import com.example.common.define.HttpHeaders;
import com.example.common.define.ShiroJWTAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component("jwtFilter")
public class JWTFilter extends BasicHttpAuthenticationFilter {

    /**
     * 判断是否带TOKEN请求
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader(HttpHeaders.AUTHORIZATION);
        return !StringUtils.isEmpty(authorization);
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorizationTokenStr = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        ShiroJWTAuthenticationToken token = new ShiroJWTAuthenticationToken(authorizationTokenStr);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 这里控制通过与否
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // OPTIONS 预请求 忽略
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }
        // 如果不带TOKEN请求，直接阻止
        if (!isLoginAttempt(request, response)) {
            throw new AuthenticationException("token is empty");
        }
        try {
            executeLogin(request, response);
        } catch (Exception e) {
            log.error("token校验失败", e);
//            ResponseUtil.result(response, Result.error(StatusCode.TOKEN_INVALID), null);
            return false;
        }
        return true;
    }

//    @Override
//    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) {
//        //获取请求头token
//        AuthenticationToken token = this.createToken(servletRequest, servletResponse);
//
//        return false;
//    }

    /**
     * 此处为AccessToken刷新，进行判断RefreshToken是否过期，未过期就返回新的AccessToken且继续正常访问
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     */
    private boolean refreshToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取header，tokenStr
        String oldToken = request.getHeader("Authorization");
        return false;
    }
}
