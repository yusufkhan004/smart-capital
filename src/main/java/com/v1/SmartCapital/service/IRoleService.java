package com.v1.SmartCapital.service;

import com.v1.SmartCapital.enums.RoleType;
import com.v1.SmartCapital.response.RoleResponse;

import java.util.List;
import java.util.UUID;


public interface IRoleService {

    RoleResponse createRole(RoleType name);

    List<RoleResponse> getAllRoles();

    RoleType getById(UUID id);
}
