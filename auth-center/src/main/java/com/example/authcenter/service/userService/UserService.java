package com.example.authcenter.service.userService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.authcenter.dao.user.User;
import com.example.authcenter.dao.user.UserWithRole;

import java.util.Optional;

public interface UserService extends IService<User> {
    /**
     * 执行登录校验,校验通过则返回token
     *
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    Optional<String> loginCheck(String userName, String password);

    UserWithRole findUserByName(String name);

    UserWithRole findUserWithRoleById(long userId);

    User findByName(String name);
}
