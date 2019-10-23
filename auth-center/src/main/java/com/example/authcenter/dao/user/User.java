package com.example.authcenter.dao.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 5382537995670726913L;
    private Long id;
    private String username;
    private String password;
    private String salt;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Long> roleIds;

    public String getCredentialsSalt() {
        return username + salt + salt;
    }

    public User() {
    }

    public User(User user) {
        BeanUtils.copyProperties(user, this);
    }
}
