package com.example.authcenter;

import com.example.authcenter.dao.user.User;
import com.example.authcenter.dao.user.UserDTO;
import com.example.authcenter.dao.user.UserMapStruct;
import com.example.common.util.JSON;
import org.junit.Test;

import java.util.Arrays;

public class MapStructTest {
    @Test
    public void userDaoTransfer() {
        User user = new User();
        user.setId(11111L)
                .setSalt("aaaaa")
                .setUsername("xujw")
                .setPassword("passwd")
                .setRoleIds(Arrays.asList(1, 2, 3, 4));

        UserDTO userDTO = UserMapStruct.INSTANCE.toUserDTO(user);
        userDTO.setEmail("54564564@qq.com");
        System.out.println(JSON.stringify(userDTO));
    }
}
