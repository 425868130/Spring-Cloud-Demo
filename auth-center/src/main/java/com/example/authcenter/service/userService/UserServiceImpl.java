package com.example.authcenter.service.userService;

import com.example.authcenter.dao.AccountAuthProfileDao;
import com.example.authcenter.dao.AccountInfoDao;
import com.example.authcenter.service.tokenService.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TokenService tokenService;

    private final AccountInfoDao accountInfoDao;
    private final AccountAuthProfileDao accountAuthProfileDao;

    public UserServiceImpl(AccountInfoDao accountInfoDao, AccountAuthProfileDao accountAuthProfileDao) {
        this.accountInfoDao = accountInfoDao;
        this.accountAuthProfileDao = accountAuthProfileDao;
    }
//
//    @Override
//    public Optional<String> loginCheck(String accountName, String password) {
//        if (StringUtils.isEmpty(accountName) || StringUtils.isEmpty(password)) {
//            return Optional.empty();
//        }
//        SysAuthProfile sysAuthProfile = sysAuthProfileDao.selectByAccountAndPassword(accountName, password);
//        if (sysAuthProfile == null) {
//            return Optional.empty();
//        }
//        return tokenService.generateToken(new UserPayload(Long.parseLong(sysAuthProfile.getUid())));
//    }
//
//    @Override
//    public UserWithRole findUserByName(String name) {
//        return withRolePermission(userMapper.getByName(name));
//    }
//
//    @Override
//    public UserWithRole findUserWithRoleById(long userId) {
//        return withRolePermission(userMapper.selectById(userId));
//    }
//
//    private UserWithRole withRolePermission(User user) {
//        Assert.notNull(user, ServiceException.withCode(StatusCode.USER_NOT_EXIST));
//        UserWithRole userWithRole = UserWithRole.fromUser(user);
//        List<SysRole> roleList = null;
//        if (!CollectionUtils.isEmpty(user.getRoleIds())) {
//            roleList = sysRoleMapper.selectBatchIds(user.getRoleIds());
//        }
//        if (CollectionUtils.isEmpty(roleList)) {
//            return userWithRole;
//        }
//        return userWithRole.setRoles(roleList.stream()
//                .filter(role -> !CollectionUtils.isEmpty(role.getPermissionIds()))
//                .map(item -> new RoleWithPermission(item, sysPermissionMapper.selectBatchIds(item.getPermissionIds())))
//                .collect(Collectors.toList()));
//    }
//
//    @Override
//    public User findByName(String name) {
//        return null;
//    }
}
