package com.example.authcenter.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "user_t")
public class User implements Serializable {

    private static final long serialVersionUID = 5382537995670726913L;
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
    private String salt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role_t", joinColumns = {@JoinColumn(name = "uid")}, inverseJoinColumns = {
            @JoinColumn(name = "rid")})
    private List<SysRole> roles;
}
