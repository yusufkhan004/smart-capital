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
    public UserDTO editUser(Long userId, UpdateUserRequest updateUserRequest) {
        logger.info("UserServiceImpl - Inside editUser method");

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty())
            throw new NotFoundException(ERROR_USER_NOT_FOUND);
        User user = optionalUser.get();
        if (!user.getEmail().equals(updateUserRequest.getEmail()) && Boolean.TRUE.equals(userRepository.existsByEmail(updateUserRequest.getEmail())))
            throw new AlreadyExistsException(ERROR_USER_WITH_SAME_EMAIL_ALREADY_EXISTS);

        try {
            user.setFirstname(updateUserRequest.getFirstname());
            user.setLastname(updateUserRequest.getLastname());
            user.setGender(CommonUtils.getGender(updateUserRequest.getGender()));
            user.setMobileNumber(Long.parseLong(updateUserRequest.getMobileNumber()));
            user.setEmail(updateUserRequest.getEmail());
            user.setLocation(updateUserRequest.getLocation());
            user.setUsername(updateUserRequest.getUsername());
            user.setIsActive(updateUserRequest.getIsActive());
            user = userRepository.save(user);
            return user.getUserDTO();
        } catch (Exception ex) {
            logger.error(EXCEPTION, ex);
            throw ex;
        }
    }

    @Override
    public UserDTO viewUser(Long userId) {
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
