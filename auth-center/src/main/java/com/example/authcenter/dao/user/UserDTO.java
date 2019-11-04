package com.example.authcenter.dao.user;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String salt;
    private List<Long> roleIdList;
    private String email;
}
