package com.example.authcenter.dao;

import com.example.authcenter.entity.Organization;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
}