package com.example.authcenter.dao;

import com.example.authcenter.entity.SysPermission;
import org.springframework.stereotype.Repository;

@Repository
public interface SysPermissionDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);
}