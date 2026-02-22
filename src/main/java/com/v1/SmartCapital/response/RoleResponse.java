package com.v1.SmartCapital.response;

import com.v1.SmartCapital.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleResponse {
    private Long id;
    private RoleType name;
}
