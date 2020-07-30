package com.example.authcenter.dao;

import com.example.authcenter.entity.DepartmentUserRelation;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentUserRelationDao {
    int insert(DepartmentUserRelation record);

    int insertSelective(DepartmentUserRelation record);
}