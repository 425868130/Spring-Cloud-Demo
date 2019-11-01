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
                .setRoleIdList(Arrays.asList(1L,2L,3L,4L));

        UserDTO userDTO = UserMapStruct.INSTANCE.toUserDTO(user);
        userDTO.setEmail("54564564@qq.com");
        System.out.println(JSON.stringify(userDTO));
    }
}
