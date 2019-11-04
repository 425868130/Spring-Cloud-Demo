package com.example.authcenter.dao.sysPermission;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 4767285034411348140L;
    private Long id;
    private String name;
}
