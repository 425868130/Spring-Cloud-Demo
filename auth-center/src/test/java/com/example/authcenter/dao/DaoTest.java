package com.example.authcenter.dao;

import com.example.authcenter.dao.user.User;
import com.example.authcenter.dao.user.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setId(1L);
        user.setUsername("xujw");
        user.setPassword("1320");
        user.setSalt("test");
        userMapper.insert(user);
    }

    @Test
    public void getById() {
        User user = userMapper.selectById(1L);
        Assert.assertNotNull("用户信息为空",user);
    }
}
