package com.v1.SmartCapital.response;

import com.v1.SmartCapital.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RoleResponse {
    private UUID id;
    private RoleType name;
}
