package com.example.authcenter.dao;

import com.example.authcenter.entity.GroupUserRelation;

public interface GroupUserRelationDao {
    int insert(GroupUserRelation record);

    int insertSelective(GroupUserRelation record);
}