package com.example.authcenter.dao;

import com.example.authcenter.entity.AccountStatusDefine;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStatusDefineDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountStatusDefine record);

    int insertSelective(AccountStatusDefine record);

    AccountStatusDefine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountStatusDefine record);

    int updateByPrimaryKey(AccountStatusDefine record);
}