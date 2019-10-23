package com.example.authcenter.dao;

import com.example.authcenter.dao.user.User;
import com.example.authcenter.dao.user.UserMapper;
import com.example.common.util.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("xujw");
        user.setPassword("1320");
        user.setSalt("test");
        userMapper.insert(user);
    }

    @Test
    public void getById() {
        User user = userMapper.selectById(1186854428227661825L);
        Assert.assertNotNull("用户信息为空", user);
        log.info(JSON.stringify(user));
    }

    @Test
    public void getByName() {
        User user = userMapper.getByName("xujw");
        Assert.assertNotNull("用户信息为空", user);
        log.info(JSON.stringify(user));
    }

    @Test
    public void update() {
        List<Long> list = new ArrayList<>();
        list.add(2L);
        User user = userMapper.selectById(1186854428227661825L);
//        user.setRoleIds(list);
        userMapper.updateById(user);
    }
}
