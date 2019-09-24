package com.example.userservice.dao;

import com.example.userservice.bean.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from user")
    List<User> list();
}
