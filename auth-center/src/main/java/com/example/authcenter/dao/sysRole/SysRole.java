package com.example.authcenter.dao.sysRole;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = 261489156171952966L;
    private Long id;
    private String role;
}