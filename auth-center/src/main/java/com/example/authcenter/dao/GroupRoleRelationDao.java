package com.example.authcenter.dao;

import com.example.authcenter.entity.GroupRoleRelation;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRoleRelationDao {
    int insert(GroupRoleRelation record);

    int insertSelective(GroupRoleRelation record);
}