package com.example.authcenter.dao.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 5382537995670726913L;
    private Long id;
    private String username;
    private String password;
    private String salt;
}
