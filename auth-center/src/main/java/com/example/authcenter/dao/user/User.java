package com.example.authcenter.dao.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.TypeHandler;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class User implements Serializable {

    private static final long serialVersionUID = 5382537995670726913L;
    private Long id;
    private String username;
    private String password;
    private String salt;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Integer> roleIds;

    public String getCredentialsSalt() {
        return username + salt + salt;
    }
}
