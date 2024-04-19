package com.gymos.web.service.impl;

import com.gymos.web.dto.RegistrationDto;
import com.gymos.web.dto.UserEntityDto;
import com.gymos.web.models.Role;
import com.gymos.web.models.UserEntity;
import com.gymos.web.repository.RoleRepository;
import com.gymos.web.repository.UserRepository;
import com.gymos.web.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.gymos.web.mapper.UserMapper.mapToUserEntityDto;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto){
        UserEntity user = new UserEntity();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserEntityDto> findAllUserEntities(){
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map((userEntity) -> mapToUserEntityDto(userEntity)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }


}
