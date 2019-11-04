package com.example.authcenter.realm;

import com.example.authcenter.dao.user.User;
import com.example.authcenter.service.tokenService.TokenService;
import com.example.authcenter.service.userService.UserService;
import com.example.common.define.ShiroJWTAuthenticationToken;
import com.example.common.define.jwt.UserPayload;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xujw 2019-10-24 14:52:15
 * 基于jwt实现的无状态鉴权
 */
@Slf4j
public class JsonWebTokenRealm extends AuthorizingRealm {
    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    public JsonWebTokenRealm(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    /**
     * 用于shiro判断token是否适用于当前realm
     *
     * @param token 要校验的token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof ShiroJWTAuthenticationToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        tokenService.parseJWT(principals.toString()).ifPresent(jwtPayload -> {
            /*获取用户信息并授权*/
            UserPayload payload = new UserPayload(jwtPayload);
            User user = userService.getById(payload.getId());
        });
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String jwt = token.getPrincipal().toString();

        return null;
    }
}
