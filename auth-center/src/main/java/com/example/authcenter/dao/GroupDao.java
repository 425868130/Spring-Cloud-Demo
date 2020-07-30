package com.example.authcenter.dao;

import com.example.authcenter.entity.Group;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDao {
    int deleteByPrimaryKey(Long id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);
}