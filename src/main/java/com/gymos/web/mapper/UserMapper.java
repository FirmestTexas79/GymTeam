package com.gymos.web.mapper;

import com.gymos.web.dto.UserEntityDto;
import com.gymos.web.models.UserEntity;

import java.util.List;

public class UserMapper {

    public static UserEntity mapToUserEntity(UserEntityDto userEntityDto){
        return UserEntity.builder()
                .id(userEntityDto.getId())
                .username(userEntityDto.getUsername())
                .email(userEntityDto.getEmail())
                .password(userEntityDto.getPassword())
                .build();
    }

    public static UserEntityDto mapToUserEntityDto(UserEntity userEntity){
        return UserEntityDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }
}
