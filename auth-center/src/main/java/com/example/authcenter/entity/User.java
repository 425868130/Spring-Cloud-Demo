package com.example.authcenter.entity;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 5382537995670726913L;

    private long id;
    private String username;
    private String password;
    private String salt;

    private List<SysRole> roles;
}
