package com.example.authcenter.dao;

import com.example.authcenter.entity.GroupUserRelation;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupUserRelationDao {
    int insert(GroupUserRelation record);

    int insertSelective(GroupUserRelation record);
}