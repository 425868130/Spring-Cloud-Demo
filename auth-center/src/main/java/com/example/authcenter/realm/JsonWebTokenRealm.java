package com.example.authcenter.realm;

import com.example.authcenter.dao.sysPermission.SysPermission;
import com.example.authcenter.dao.sysRole.RoleWithPermission;
import com.example.authcenter.dao.user.User;
import com.example.authcenter.dao.user.UserWithRole;
import com.example.authcenter.service.tokenService.TokenService;
import com.example.authcenter.service.userService.UserService;
import com.example.common.define.ShiroJWTAuthenticationToken;
import com.example.common.define.jwt.JwtPayload;
import com.example.common.define.jwt.UserPayload;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author xujw 2019-10-24 14:52:15
 * 基于jwt实现的无状态鉴权
 */
@Slf4j
@Component
public class JsonWebTokenRealm extends AuthorizingRealm {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

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

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String tokenStr = token.getPrincipal().toString();
        Optional<JwtPayload> jwtPayload = tokenService.parseJWT(tokenStr);
        if (!jwtPayload.isPresent() || tokenService.inBlackList(jwtPayload.orElse(null))) {
            throw new AuthenticationException("token invalid");
        }
        return new SimpleAuthenticationInfo(tokenStr, tokenStr, getName());
    }

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        tokenService.parseJWT(principals.toString()).ifPresent(jwtPayload -> {
            /*获取用户信息并授权*/
            UserPayload payload = new UserPayload(jwtPayload);
            if (!payload.getUserId().isPresent()) {
                return;
            }
            UserWithRole user = userService.findUserWithRoleById(payload.getUserId().get());
            for (RoleWithPermission role : user.getRoles()) {
                authorizationInfo.addRole(role.getRole());
                for (SysPermission permission : role.getPermissions()) {
                    authorizationInfo.addStringPermission(permission.getName());
                }
            }
        });
        return authorizationInfo;
    }
}
