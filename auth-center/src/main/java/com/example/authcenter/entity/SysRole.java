package com.example.authcenter.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = 261489156171952966L;
    private Integer id;
    private String role;
    private List<SysPermission> permissions;
    private List<User> users;
}