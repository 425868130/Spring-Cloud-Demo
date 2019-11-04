package com.example.authcenter.dao.sysRole;


import com.example.authcenter.dao.sysPermission.SysPermission;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RoleWithPermission extends SysRole {

    private static final long serialVersionUID = 3414654787073421895L;

    private List<SysPermission> permissions;

    public RoleWithPermission(SysRole sysRole, List<SysPermission> permissionList) {
        super(sysRole);
        permissions = permissionList;
    }
}
