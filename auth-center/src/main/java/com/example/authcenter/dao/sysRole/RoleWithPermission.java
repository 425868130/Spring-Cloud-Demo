package com.example.authcenter.dao.sysRole;

import com.example.authcenter.dao.sysPermission.SysPermission;
import lombok.Data;

import java.util.List;

@Data
public class RoleWithPermission extends SysRole {

    private static final long serialVersionUID = 3414654787073421895L;

    List<SysPermission> permissions;
}
