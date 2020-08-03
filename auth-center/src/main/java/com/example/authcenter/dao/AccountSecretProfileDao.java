package com.example.authcenter.dao;

import com.example.authcenter.entity.AccountSecretProfile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountSecretProfileDao {
    int deleteByPrimaryKey(Long uid);

    int insert(AccountSecretProfile record);

    int insertSelective(AccountSecretProfile record);

    int updateByPrimaryKeySelective(AccountSecretProfile record);

    int updateByPrimaryKey(AccountSecretProfile record);

    int countByUidPassword(@Param("uid") long uid, @Param("password") String password);

    String selectSaltByPrimaryKey(long uid);
}