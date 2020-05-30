package com.example.authcenter.dao;

import com.example.authcenter.entity.SysAuthProfile;

public interface SysAuthProfileDao {
    int deleteByPrimaryKey(String uid);

    int insert(SysAuthProfile record);

    int insertSelective(SysAuthProfile record);

    SysAuthProfile selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(SysAuthProfile record);

    int updateByPrimaryKey(SysAuthProfile record);
}