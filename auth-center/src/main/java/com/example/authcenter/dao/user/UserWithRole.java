package com.example.authcenter.dao.user;

import com.example.authcenter.dao.sysRole.RoleWithPermission;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author xujw 2019-11-1 09:49:07
 * 带角色权限的用户类
 */
@Data
@Accessors(chain = true)
public class UserWithRole {

    private Long id;
    private String username;
    private String salt;
    private List<Integer> roleIds;
    // 角色权限列表
    private List<RoleWithPermission> roles;

    public static UserWithRole fromUser(User user) {
        return UserMapStruct.INSTANCE.mapUserWithRole(user);
    }
}
