package com.example.authcenter.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 4767285034411348140L;
    private Integer id;
    private String name;
    private List<SysRole> roles;
}
