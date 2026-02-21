package com.v1.SmartCapital.request;

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
public class UpdateUserDetailsRequest {
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Enter valid First Name")
    String firstname;

    @Pattern(regexp = "^[a-zA-Z]*$", message = "Enter valid Last Name")
    String lastname;

    @Pattern(regexp = "^[6789]\\d{9}$", message = "Mobile number should contain 10 digits")
    String mobileNumber;

    @Pattern(regexp = "(^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$)", message = "Enter valid Email-Id")
    String emailId;
}
