package com.v1.SmartCapital.dto;


import com.v1.SmartCapital.enums.Gender;
import com.v1.SmartCapital.enums.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    Long id;
    String firstname;
    String lastname;
    String fullname;
    @Enumerated(EnumType.STRING)
    Gender gender;
    Long mobileNumber;
    String countryCode;
    String email;
    String location;
    String username;
    Boolean isActive;
    Long noOfRolesAdded;
    Long roleId;
    String role;
    String parentUsername;
    Long parentUserId;
    String teamLeadUsername;
    String adminUsername;
    LocalDateTime dateCreated;
    LocalDateTime lastUpdated;
    @Enumerated(EnumType.STRING)
    Map<String,String> status = new HashMap<>();
    String createdBy;
    Boolean isFirstTime;
    UserType userType;

    public UserDTO(Long id, String firstname, String lastname, Gender gender, Long mobileNumber,String countryCode, String email, String location,
                   String username, Boolean isActive, LocalDateTime dateCreated, LocalDateTime lastUpdated, Map<String,String> status, String createdBy,Boolean isFirstTime, UserType userType){
        this.id=id;
        this.firstname=firstname;
        this.lastname=lastname;
        this.fullname = firstname + " " + lastname;
        this.gender=gender;
        this.mobileNumber=mobileNumber;
        this.countryCode = countryCode;
        this.email=email;
        this.location=location;
        this.username=username;
        this.isActive=isActive;
        this.dateCreated=dateCreated;
        this.lastUpdated=lastUpdated;
        this.status = status;
        this.noOfRolesAdded = 1l;
        this.createdBy = createdBy;
        this.isFirstTime = isFirstTime;
        this.userType = userType;
    }
}
