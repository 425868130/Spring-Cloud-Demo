package com.example.authcenter.dao.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapStruct {
    UserMapStruct INSTANCE = Mappers.getMapper(UserMapStruct.class);

    UserWithRole mapUserWithRole(User user);

    UserDTO toUserDTO(User user);
}
