package com.example.authcenter.service.userService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.authcenter.dao.user.User;
import com.example.authcenter.dao.user.UserWithRole;

public interface UserService extends IService<User> {
    UserWithRole findUserByName(String name);
    User findByName(String name);
}
