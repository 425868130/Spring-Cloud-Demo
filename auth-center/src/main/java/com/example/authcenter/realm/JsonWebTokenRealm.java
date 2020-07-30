package com.example.authcenter.realm;

import com.example.authcenter.service.tokenService.TokenService;
import com.example.authcenter.service.userService.UserService;
import com.example.common.define.ShiroJWTAuthenticationToken;
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

import javax.servlet.http.HttpServletRequest;

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
    @Autowired
    private HttpServletRequest httpServletRequest;

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
//        String tokenStr = token.getPrincipal().toString();
//        Optional<JwtPayload> jwtPayload = tokenService.parseJWT(tokenStr);
//        if (!jwtPayload.isPresent() || tokenService.inBlackList(jwtPayload.orElse(null))) {
//            throw new AuthenticationException("token invalid");
//        }
//        long userId = (long) jwtPayload.get().get(UserPayload.Key.USER_ID);
//        /*验证通过后将用户信息放到请求中方便续Controller直接使用避免重复解析token*/
//        httpServletRequest.setAttribute("userId", userService.findUserWithRoleById(userId));
//        return new SimpleAuthenticationInfo(tokenStr, tokenStr, getName());
        return new SimpleAuthenticationInfo(null, null, getName());
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
//        tokenService.parseJWT(principals.toString()).ifPresent(jwtPayload -> {
//            /*获取用户信息并授权*/
//            UserPayload payload = new UserPayload(jwtPayload);
//            if (!payload.getUserId().isPresent()) {
//                return;
//            }
//            UserWithRole user = userService.findUserWithRoleById(payload.getUserId().get());
//            for (RoleWithPermission role : user.getRoles()) {
//                authorizationInfo.addRole(role.getRole());
//                for (SysPermission permission : role.getPermissions()) {
//                    authorizationInfo.addStringPermission(permission.getName());
//                }
//            }
//        });
        return authorizationInfo;
    }
}
