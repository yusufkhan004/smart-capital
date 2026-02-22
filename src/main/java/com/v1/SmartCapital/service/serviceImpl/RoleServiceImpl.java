package com.v1.SmartCapital.service.serviceImpl;

import com.v1.SmartCapital.entity.Role;
import com.v1.SmartCapital.enums.RoleType;
import com.v1.SmartCapital.exception.AlreadyExistsException;
import com.v1.SmartCapital.repository.RoleRepository;
import com.v1.SmartCapital.response.RoleResponse;
import com.v1.SmartCapital.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;

    public RoleResponse createRole(RoleType name) {
        logger.info("RoleServiceImpl - Inside createRole method");
        if (roleRepository.findByName(name).isPresent()) {
            throw new AlreadyExistsException("Role already exists: " + name);
        }

        try {
            Role role = roleRepository.save(new Role(name));
            return new RoleResponse(role.getId(), role.getName());
        } catch (Exception ex) {
            throw ex;
        }
    }

}
