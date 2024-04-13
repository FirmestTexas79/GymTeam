package com.gymos.web.repository;

import com.gymos.web.models.Role;
import com.gymos.web.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
