package com.example.authcenter.dao;

import com.example.authcenter.entity.AccountRoleRelation;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleRelationDao {
    int insert(AccountRoleRelation record);

    int insertSelective(AccountRoleRelation record);
}