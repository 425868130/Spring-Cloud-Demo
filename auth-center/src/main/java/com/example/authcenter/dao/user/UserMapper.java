package com.example.authcenter.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {
    /*定义不含用户密码的字段*/
    String WithoutPassword = "id,username,salt,role_ids";

    @Select({"select",
            WithoutPassword,
            "from user where username = #{name}"})
    User getByName(@Param("name") String name);

    @Select({"select",
            WithoutPassword,
            "from user where username = #{userName} and password = #{password}"})
    User userPasswordCheck(@Param("userName") String userName, @Param("password") String password);
}
