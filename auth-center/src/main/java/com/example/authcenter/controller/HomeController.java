package com.example.authcenter.controller;

import com.example.authcenter.dao.user.User;
import com.example.authcenter.service.userService.UserService;
import com.example.authcenter.util.PasswordHelper;
import com.example.common.define.HttpHeaders;
import com.example.common.define.Result;
import com.example.common.define.ShiroJWTAuthenticationToken;
import com.example.common.define.StatusCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordHelper passwordHelper;

    @GetMapping("login")
    public Object login() {
        return "Here is Login page";
    }

    @GetMapping("unauthc")
    public Object unauthc() {
        return "Here is Unauthc page";
    }

    @GetMapping("doLogin")
    public Result doLogin(@RequestParam String username, @RequestParam String password) {
        Optional<String> token = userService.loginCheck(username, password);
        return Result.success(token.orElse(""));
    }

    @RequestMapping("getUserAuth")
    public Result getUserAuth(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        String tokenStr = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(tokenStr)) {
            return Result.error(StatusCode.UN_LOGIN);
        }
        ShiroJWTAuthenticationToken jwtAuthenticationToken = new ShiroJWTAuthenticationToken(tokenStr);

        User user = (User) subject.getSession().getAttribute("user");
        return Result.success();
    }

    @GetMapping("register")
    public Object register(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        passwordHelper.encryptPassword(user);

        userService.save(user);
        return "SUCCESS";
    }
}
