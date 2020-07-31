package com.example.authcenter.dao;

import com.example.authcenter.entity.AccountSecretProfile;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountSecretProfileDao {
    int deleteByPrimaryKey(Long uid);

    int insert(AccountSecretProfile record);

    int insertSelective(AccountSecretProfile record);

    AccountSecretProfile selectByPrimaryKey(Long uid);

    int updateByPrimaryKeySelective(AccountSecretProfile record);

    int updateByPrimaryKey(AccountSecretProfile record);
}