package com.v1.SmartCapital.controller;

import com.v1.SmartCapital.dto.RoleRequest;
import com.v1.SmartCapital.service.IRoleService;
import com.v1.SmartCapital.util.ResponseEntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.v1.SmartCapital.constants.GeneralMsgConstants.MSG_ROLE_CREATED;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private static final Logger logger = LogManager.getLogger(RoleController.class);

    @Autowired
    IRoleService roleService;

    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody RoleRequest roleRequest) {
        logger.info("RoleController- inside createRole method");
        return ResponseEntityUtils.get(
                roleService.createRole(roleRequest.getName()),
                MSG_ROLE_CREATED);
    }
}
