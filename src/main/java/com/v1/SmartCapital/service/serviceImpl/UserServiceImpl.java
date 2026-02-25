package com.v1.SmartCapital.service.serviceImpl;


import com.v1.SmartCapital.dto.UserDTO;
import com.v1.SmartCapital.entity.User;
import com.v1.SmartCapital.enums.UserType;
import com.v1.SmartCapital.exception.AlreadyExistsException;
import com.v1.SmartCapital.exception.NotFoundException;
import com.v1.SmartCapital.repository.UserRepository;
import com.v1.SmartCapital.request.UpdateUserRequest;
import com.v1.SmartCapital.request.UserRequest;
import com.v1.SmartCapital.response.TeamMemberResponse;
import com.v1.SmartCapital.service.IUserService;
import com.v1.SmartCapital.util.CommonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.v1.SmartCapital.constants.ErrorMsgConstants.*;
import static com.v1.SmartCapital.constants.ValidationConstants.USER_MOBILE_COUNTRY_CODE;

@Service
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO addUser(UserRequest userRequest) {
        logger.info("UserServiceImpl - Inside addUser method");

        Optional<User> optionalUser = userRepository.findByEmail(userRequest.getEmail());
        if (optionalUser.isPresent() && (optionalUser.get().getEmail().equals(userRequest.getEmail())))
            throw new AlreadyExistsException(ERROR_USER_WITH_SAME_EMAIL_ALREADY_EXISTS);
        User user = new User(userRequest.getFirstname(), userRequest.getLastname(), CommonUtils.getGender(userRequest.getGender()), Long.parseLong(userRequest.getMobileNumber()), USER_MOBILE_COUNTRY_CODE,
                userRequest.getEmail(), userRequest.getLocation(), userRequest.getUsername(), CommonUtils.encodePassword(userRequest.getPassword()), Boolean.TRUE, UserType.INTERNAL);

        try {
            user = userRepository.save(user);
            return user.getUserDTO();
        } catch (Exception ex) {
            logger.error(EXCEPTION, ex);
            userRepository.deleteById(user.getId());
            throw ex;
        }
    }

    @Override
    public List<UserDTO> getAllUser() {
        logger.info("UserServiceImpl - Inside getAllUser method");

        List<User> userList = userRepository.findAll();

        if (!userList.isEmpty())
            return userList.stream().map(User::getUserDTO).collect(Collectors.toList());
        return Collections.emptyList();
    }

    @Override
    public UserDTO editUser(UUID userId, UpdateUserRequest updateUserRequest) {
        logger.info("UserServiceImpl - Inside editUser method");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ERROR_USER_NOT_FOUND));

        // Email uniqueness check only if email is provided and changed
        if (updateUserRequest.getEmail() != null && !updateUserRequest.getEmail().isEmpty()) {
            if (!user.getEmail().equals(updateUserRequest.getEmail()) &&
                    Boolean.TRUE.equals(userRepository.existsByEmail(updateUserRequest.getEmail()))) {
                throw new AlreadyExistsException(ERROR_USER_WITH_SAME_EMAIL_ALREADY_EXISTS);
            }
        }

        try {
            // TUNE: Conditional setters to maintain old records
            if (updateUserRequest.getFirstname() != null && !updateUserRequest.getFirstname().isBlank()) {
                user.setFirstname(updateUserRequest.getFirstname());
            }
            if (updateUserRequest.getLastname() != null && !updateUserRequest.getLastname().isBlank()) {
                user.setLastname(updateUserRequest.getLastname());
            }
            if (updateUserRequest.getGender() != null && !updateUserRequest.getGender().isBlank()) {
                user.setGender(CommonUtils.getGender(updateUserRequest.getGender()));
            }
            if (updateUserRequest.getMobileNumber() != null && !updateUserRequest.getMobileNumber().isBlank()) {
                user.setMobileNumber(Long.parseLong(updateUserRequest.getMobileNumber()));
            }
            if (updateUserRequest.getEmail() != null && !updateUserRequest.getEmail().isBlank()) {
                user.setEmail(updateUserRequest.getEmail());
            }
            if (updateUserRequest.getLocation() != null && !updateUserRequest.getLocation().isBlank()) {
                user.setLocation(updateUserRequest.getLocation());
            }
            if (updateUserRequest.getUsername() != null && !updateUserRequest.getUsername().isBlank()) {
                user.setUsername(updateUserRequest.getUsername());
            }
            if (updateUserRequest.getIsActive() != null) {
                user.setIsActive(updateUserRequest.getIsActive());
            }

            user = userRepository.save(user);
            return user.getUserDTO();

        } catch (Exception ex) {
            logger.error(EXCEPTION, ex);
            throw ex;
        }
    }

    @Override
    public UserDTO viewUser(UUID userId) {
        logger.info("UserServiceImpl - Inside viewUser method");
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty())
            throw new NotFoundException(ERROR_USER_NOT_FOUND);
        UserDTO userDTO = optionalUser.get().getUserDTO();
        return userDTO;
    }

    @Override
    public List<TeamMemberResponse> fetchAllUsers() {
        logger.info("UserServiceImpl - Inside fetchAllUsers method");
        List<User> userList = userRepository.findAll();
        return userList.stream().map(u -> new TeamMemberResponse(u.getUsername(), u.getId())).collect(Collectors.toList());
    }
}
