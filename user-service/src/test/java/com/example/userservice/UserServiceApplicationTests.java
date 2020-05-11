package com.example.userservice;

import com.example.common.util.JSON;
import com.example.userservice.bean.User;
import com.example.userservice.dao.UserMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@SpringBootTest
public class UserServiceApplicationTests {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.list();
        Assertions.assertEquals(5, userList.size());
        String userListJson = JSON.stringifyPretty(userList);
        System.out.println(userListJson);

        List<User> userList2 = JSON.parseCollection(userListJson, new TypeReference<List<User>>() {
        });
        List<User> userList3 = JSON.parseCollection(userListJson, List.class, User.class);

        List<User> userList4 = JSON.parseList(userListJson, User.class);
    }

}
