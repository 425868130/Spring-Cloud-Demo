package com.example.authcenter.dao.user;

import com.example.authcenter.dao.sysRole.RoleWithPermission;
import lombok.Data;

import java.util.List;

@Data
public class UserWithRole extends User {
    private static final long serialVersionUID = -6102731252097125802L;
    private List<RoleWithPermission> roles;

    public UserWithRole(User user, List<RoleWithPermission> roles) {
        super(user);
        this.roles = roles;
    }
}