package com.v1.SmartCapital.service;

import com.v1.SmartCapital.enums.RoleType;
import com.v1.SmartCapital.response.RoleResponse;


public interface IRoleService {

    RoleResponse createRole(RoleType name);
}
