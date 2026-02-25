package com.v1.SmartCapital.service;

import com.v1.SmartCapital.dto.UserDTO;
import com.v1.SmartCapital.request.UpdateUserRequest;
import com.v1.SmartCapital.request.UserRequest;
import com.v1.SmartCapital.response.TeamMemberResponse;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    UserDTO addUser(UserRequest userRequest);

    List<UserDTO> getAllUser();

    UserDTO editUser(UUID userId, UpdateUserRequest updateUserRequest);

    UserDTO viewUser(UUID userId);

    List<TeamMemberResponse> fetchAllUsers();
}
