package com.example.authcenter.dao;

import com.example.authcenter.entity.UserStatusDefine;

public interface UserStatusDefineDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserStatusDefine record);

    int insertSelective(UserStatusDefine record);

    UserStatusDefine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserStatusDefine record);

    int updateByPrimaryKey(UserStatusDefine record);
}