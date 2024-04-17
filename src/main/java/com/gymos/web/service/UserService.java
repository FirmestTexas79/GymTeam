package com.gymos.web.service;

import com.gymos.web.dto.RegistrationDto;
import com.gymos.web.dto.UserEntityDto;
import com.gymos.web.models.UserEntity;

import java.util.List;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

    List<UserEntityDto> findAllUserEntities();

    void delete(Long userId);
}
