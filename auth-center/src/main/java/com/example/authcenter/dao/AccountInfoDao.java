package com.example.authcenter.dao;

import com.example.authcenter.entity.AccountInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountInfoDao {
    int deleteByPrimaryKey(Long uid);

    int insert(AccountInfo record);

    int insertSelective(AccountInfo record);

    AccountInfo selectByPrimaryKey(Long uid);

    int updateByPrimaryKeySelective(AccountInfo record);

    int updateByPrimaryKey(AccountInfo record);
}