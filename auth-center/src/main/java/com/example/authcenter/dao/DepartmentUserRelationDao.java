package com.example.authcenter.dao;

import com.example.authcenter.entity.DepartmentUserRelation;

public interface DepartmentUserRelationDao {
    int insert(DepartmentUserRelation record);

    int insertSelective(DepartmentUserRelation record);
}