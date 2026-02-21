package com.v1.SmartCapital.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class UserRequest {

    @Schema(example = "john", description = "First name of the user (alphabets only)")
    @NotEmpty(message = "Enter valid First Name")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Enter valid First Name")
    String firstname;

    @Schema(example = "kennedy", description = "Last name of the user (alphabets only)")
    @NotEmpty(message = "Enter valid Last Name")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Enter valid Last Name")
    String lastname;

    @Schema(example = "Male", allowableValues = {"Male", "Female", "Others"})
    @NotEmpty(message = "Enter valid Gender")
    @Pattern(regexp = "Male|Female|Others", message = "Invalid Gender")
    String gender;

    @Schema(example = "9594116523", description = "10-digit Indian mobile number")
    @NotNull(message = "Enter valid Mobile Number")
    @Pattern(regexp = "^[6789]\\d{9}$", message = "Mobile number should contain 10 digits")
    String mobileNumber;

    @Schema(example = "john.kennedy@example.com", description = "Valid email address")
    @Pattern(regexp = "^\\S+@\\S+\\.\\S+$", message = "Enter valid Email-Id")
    @NotEmpty(message = "Enter valid Email-Id")
    String email;

    @Schema(example = "Mumbai", description = "Current city or location")
    @NotEmpty(message = "Enter valid Location")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Enter valid Location")
    String location;

    @Schema(example = "john004", description = "Unique username for login")
    @NotEmpty(message = "Enter valid username")
    String username;

    @Schema(example = "Pass@1234", description = "8-16 characters with Uppercase, Lowercase, Number, and Special Char")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=*_])(?=\\S+$).{8,16}$",
            message = "Password must contain atleast 1 Uppercase, 1 Lowercase, 1 Special character and 1 Digit.")
    @NotEmpty(message = "Enter valid Password")
    String password;
}