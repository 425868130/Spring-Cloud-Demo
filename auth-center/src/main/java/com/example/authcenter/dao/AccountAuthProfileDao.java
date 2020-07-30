package com.example.authcenter.dao;

import com.example.authcenter.entity.AccountAuthProfile;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountAuthProfileDao {
    int deleteByPrimaryKey(String uid);

    int insert(AccountAuthProfile record);

    int insertSelective(AccountAuthProfile record);

    AccountAuthProfile selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(AccountAuthProfile record);

    int updateByPrimaryKey(AccountAuthProfile record);
}