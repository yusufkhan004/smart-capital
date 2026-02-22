package com.v1.SmartCapital.repository;

import com.v1.SmartCapital.entity.Role;
import com.v1.SmartCapital.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleType name);
}
