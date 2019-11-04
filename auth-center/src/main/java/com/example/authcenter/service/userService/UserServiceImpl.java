package com.example.authcenter.service.userService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.authcenter.dao.sysPermission.SysPermissionMapper;
import com.example.authcenter.dao.sysRole.RoleWithPermission;
import com.example.authcenter.dao.sysRole.SysRole;
import com.example.authcenter.dao.sysRole.SysRoleMapper;
import com.example.authcenter.dao.user.User;
import com.example.authcenter.dao.user.UserMapper;
import com.example.authcenter.dao.user.UserWithRole;
import com.example.common.define.ServiceException;
import com.example.common.define.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public UserWithRole findUserByName(String name) {
        User user = userMapper.getByName(name);
        Assert.notNull(user, ServiceException.withCode(StatusCode.USER_NOT_EXIST));
        UserWithRole userWithRole = UserWithRole.fromUser(user);
        List<SysRole> roleList = null;
        if (!CollectionUtils.isEmpty(user.getRoleIds())) {
            roleList = sysRoleMapper.selectBatchIds(user.getRoleIds());
        }
        if (CollectionUtils.isEmpty(roleList)) {
            return userWithRole;
        }
        return userWithRole.setRoles(roleList.stream()
                .filter(role -> !CollectionUtils.isEmpty(role.getPermissionIds()))
                .map(item -> new RoleWithPermission(item, sysPermissionMapper.selectBatchIds(item.getPermissionIds())))
                .collect(Collectors.toList()));
    }

    @Override
    public User findByName(String name) {
        return null;
    }
}
