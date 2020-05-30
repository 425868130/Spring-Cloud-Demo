package com.example.authcenter.dao;

import com.example.authcenter.entity.UserStatus;

public interface UserStatusDao {
    int insert(UserStatus record);

    int insertSelective(UserStatus record);
}