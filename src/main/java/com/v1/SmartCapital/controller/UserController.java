package com.v1.SmartCapital.controller;


import com.v1.SmartCapital.dto.ResponseDTO;
import com.v1.SmartCapital.dto.UserDTO;
import com.v1.SmartCapital.request.UpdateUserRequest;
import com.v1.SmartCapital.request.UserRequest;
import com.v1.SmartCapital.service.IUserService;
import com.v1.SmartCapital.util.ResponseEntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.v1.SmartCapital.constants.GeneralMsgConstants.*;


@RestController
@RequestMapping("/v1/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addUser(@RequestBody UserRequest userRequest) {
        logger.info("UserController- inside addUser method");
        return ResponseEntityUtils.get(userService.addUser(userRequest), MSG_USER_ADDED);
    }

    @PostMapping("/edit-user/{userId}")
    public ResponseEntity<ResponseDTO> editUser(@PathVariable UUID userId, @RequestBody UpdateUserRequest updateUserRequest){
        logger.info("UserController- inside editUser method");
        return ResponseEntityUtils.get(userService.editUser(userId,updateUserRequest), MSG_USER_UPDATED);
    }

    @GetMapping("/get-all-user")
    public List<UserDTO> getAllUser() {
        logger.info("UserController- inside getAllUser method");
        return userService.getAllUser();
    }

    @GetMapping("/view-user/{userId}")
    public ResponseEntity<ResponseDTO> viewUser(@PathVariable UUID userId){
        logger.info("UserController - Inside viewUser method");
        return ResponseEntityUtils.get(userService.viewUser(userId),MSG_SUCCESS);
    }

    //Api to get all users except the loggedIn user
    @GetMapping("/fetch-all-usernames")
    public ResponseEntity<ResponseDTO> fetchAllUsers(){
        logger.info("UserController - Inside fetchAllUsers method");
        return ResponseEntityUtils.get(userService.fetchAllUsers(),MSG_ALL_MEMBERS_FETCHED);
    }
}
