package com.v1.SmartCapital.controller;

import com.v1.SmartCapital.dto.ResponseDTO;
import com.v1.SmartCapital.dto.RoleRequestDTO;
import com.v1.SmartCapital.response.RoleResponse;
import com.v1.SmartCapital.service.IRoleService;
import com.v1.SmartCapital.util.ResponseEntityUtils;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.v1.SmartCapital.constants.GeneralMsgConstants.*;

@RestController
@RequestMapping("/v1/role")
@RequiredArgsConstructor
public class RoleController {

    private static final Logger logger = LogManager.getLogger(RoleController.class);

    private final IRoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> createRole(@RequestBody RoleRequestDTO roleRequestDTO) {
        logger.info("RoleController- inside createRole method");
        return ResponseEntityUtils.get(
                roleService.createRole(roleRequestDTO.getName()),
                MSG_ROLE_CREATED);
    }

    @GetMapping("/get-all-role")
    public List<RoleResponse> getAllRoles() {
        logger.info("xRoleController- inside getAllRole method");
        return roleService.getAllRoles();
    }

    @GetMapping("/view-role/{roleId}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable UUID roleId){
        logger.info("UserController - Inside viewUser method");
        return ResponseEntityUtils.get(roleService.getById(roleId),MSG_SUCCESS);
    }
}
