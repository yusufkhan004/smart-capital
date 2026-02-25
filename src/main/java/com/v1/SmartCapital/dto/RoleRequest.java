package com.v1.SmartCapital.dto;

import com.v1.SmartCapital.enums.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoleRequest {

    private RoleType name;
}
