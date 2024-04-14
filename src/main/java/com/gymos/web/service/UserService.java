package com.gymos.web.service;

import com.gymos.web.dto.RegistrationDto;
import com.gymos.web.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsename(String username);
}
