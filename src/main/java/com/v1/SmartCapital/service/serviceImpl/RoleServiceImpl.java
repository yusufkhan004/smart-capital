package com.v1.SmartCapital.service.serviceImpl;

import com.v1.SmartCapital.entity.Role;
import com.v1.SmartCapital.enums.RoleType;
import com.v1.SmartCapital.exception.AlreadyExistsException;
import com.v1.SmartCapital.exception.NotFoundException;
import com.v1.SmartCapital.repository.RoleRepository;
import com.v1.SmartCapital.response.RoleResponse;
import com.v1.SmartCapital.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.v1.SmartCapital.constants.ErrorMsgConstants.ERROR_ROLE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;

    @Override
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

    @Override
    public List<RoleResponse> getAllRoles() {
        logger.info("RoleServiceImpl - Inside getAllRoles method");

        List<Role> roleList = roleRepository.findAll();

        if (!roleList.isEmpty())
            return roleList.stream()
                    .map(Role::getRoleResponse)
                    .collect(Collectors.toList());
        return Collections.emptyList();
    }

    @Override
    public RoleType getById(UUID roleId) {
        return roleRepository.findById(roleId)
                .map(Role::getName)
                .orElseThrow(() -> new NotFoundException(ERROR_ROLE_NOT_FOUND));
    }
}
