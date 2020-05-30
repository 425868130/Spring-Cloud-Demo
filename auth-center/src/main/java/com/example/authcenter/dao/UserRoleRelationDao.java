package com.example.authcenter.dao;

import com.example.authcenter.entity.UserRoleRelation;

public interface UserRoleRelationDao {
    int insert(UserRoleRelation record);

    int insertSelective(UserRoleRelation record);
}