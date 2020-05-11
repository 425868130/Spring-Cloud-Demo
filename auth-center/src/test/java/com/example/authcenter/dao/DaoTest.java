package com.example.authcenter.dao;

import com.example.authcenter.dao.user.User;
import com.example.authcenter.dao.user.UserMapper;
import com.example.common.util.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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
        Assertions.assertNotNull(user, "用户信息为空");
        log.info(JSON.stringify(user));
    }

    @Test
    public void getByName() {
        User user = userMapper.getByName("xujw");
        Assertions.assertNotNull(user, "用户信息为空");
        log.info(JSON.stringify(user));
    }

    @Test
    public void update() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        User user = userMapper.selectById(1186854428227661825L);
        user.setRoleIds(list);
        userMapper.updateById(user);
    }
}
