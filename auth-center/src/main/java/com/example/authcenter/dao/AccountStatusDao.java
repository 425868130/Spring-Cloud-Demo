package com.example.authcenter.dao;

import com.example.authcenter.entity.AccountStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStatusDao {
    int insert(AccountStatus record);

    int insertSelective(AccountStatus record);
}