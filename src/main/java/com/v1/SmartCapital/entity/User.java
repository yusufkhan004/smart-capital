package com.v1.SmartCapital.entity;

import com.v1.SmartCapital.audit.Auditable;
import com.v1.SmartCapital.dto.UserDTO;
import com.v1.SmartCapital.enums.Gender;
import com.v1.SmartCapital.enums.UserStatus;
import com.v1.SmartCapital.enums.UserType;
import com.v1.SmartCapital.util.CommonUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static com.v1.SmartCapital.util.CommonUtils.getEnumMap;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "\"user\"")
@Entity
public class User extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 255)
    String firstname;
    @Column(length = 255)
    String lastname;
    @Enumerated(EnumType.STRING)
    Gender gender;
    Long mobileNumber;
    String countryCode;
    String email;
    String location;
    @Column(length = 255)
    String username;
    String password;
    Boolean isActive = Boolean.TRUE;
    @Enumerated(EnumType.STRING)
    UserStatus status;
    Boolean isFirstTime;
    @Enumerated(EnumType.STRING)
    UserType userType;

    public User(String firstname, String lastname, Gender gender, Long mobileNumber, String countryCode, String email, String location, String username, String password, Boolean isFirstTime, UserType userType) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.countryCode = countryCode;
        this.email = email;
        this.location = location;
        this.username = username;
        this.password = password;
        this.isFirstTime = isFirstTime;
        this.userType = userType;
    }

    public UserDTO getUserDTO() {
        return new UserDTO(id, firstname, lastname, gender, mobileNumber, countryCode,email, location, username, isActive, creationDate, lastModifiedDate, getEnumMap(status.name(),status.value()), CommonUtils.getUsername(createdBy),isFirstTime, userType);
    }
}



