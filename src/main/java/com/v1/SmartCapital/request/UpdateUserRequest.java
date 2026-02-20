package com.v1.SmartCapital.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserRequest {

    @NotEmpty(message = "Enter valid First Name")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Enter valid First Name")
    String firstname;

    @NotEmpty(message = "Enter valid Last Name")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Enter valid Last Name")
    String lastname;

    @NotEmpty(message = "Enter valid Gender")
    @Pattern(regexp = "Male|Female|Others", message = "Invalid Gender")
    String gender;

    @NotNull(message = "Enter valid Mobile Number")
    @Pattern(regexp = "^[6789]\\d{9}$", message = "Mobile number should contain 10 digits")
    String mobileNumber;

    @Pattern(regexp = "(^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$)", message = "Enter valid Email-Id")
    @NotEmpty(message = "Enter valid Email-Id")
    String email;

    @NotEmpty(message = "Enter valid Location")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Enter valid Location")
    String location;

    @NotEmpty(message = "Enter valid username")
    String username;

    Boolean isActive = Boolean.TRUE;
}
