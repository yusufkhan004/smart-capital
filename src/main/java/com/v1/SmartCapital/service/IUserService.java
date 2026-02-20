package com.v1.SmartCapital.service;

import com.v1.SmartCapital.dto.UserDTO;
import com.v1.SmartCapital.request.UpdateUserRequest;
import com.v1.SmartCapital.request.UserRequest;
import com.v1.SmartCapital.response.TeamMemberResponse;

import java.util.List;

public interface IUserService {
    UserDTO addUser(UserRequest userRequest);

    List<UserDTO> getAllUser();

    UserDTO editUser(Long userId, UpdateUserRequest updateUserRequest);

    UserDTO viewUser(Long userId);

    List<TeamMemberResponse> fetchAllUsers();
}
