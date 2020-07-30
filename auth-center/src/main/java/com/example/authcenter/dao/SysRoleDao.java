package com.example.authcenter.dao;

import com.example.authcenter.entity.SysRole;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}