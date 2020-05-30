package com.example.authcenter.dao;

import com.example.authcenter.entity.GroupRoleRelation;

public interface GroupRoleRelationDao {
    int insert(GroupRoleRelation record);

    int insertSelective(GroupRoleRelation record);
}