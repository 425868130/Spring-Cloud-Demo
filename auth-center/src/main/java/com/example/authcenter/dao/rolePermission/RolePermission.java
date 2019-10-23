package com.example.authcenter.dao.rolePermission;

import java.io.Serializable;

public class RolePermission implements Serializable {

    private static final long serialVersionUID = -1361041626581568498L;
    private long role;
    private long permission;


    public long getRole() {
        return role;
    }

    public void setRole(long role) {
        this.role = role;
    }


    public long getPermission() {
        return permission;
    }

    public void setPermission(long permission) {
        this.permission = permission;
    }

}
