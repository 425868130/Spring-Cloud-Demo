package com.example.authcenter.service.userService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.authcenter.dao.rolePermission.RolePermissionMapper;
import com.example.authcenter.dao.sysRole.SysRoleMapper;
import com.example.authcenter.dao.user.User;
import com.example.authcenter.dao.user.UserMapper;
import com.example.authcenter.dao.user.UserWithRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public UserWithRole findUserByName(String name) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("name", name);
        userMapper.selectByMap(condition);
        User user = userMapper.getByName(name);
        return null;
    }
}
