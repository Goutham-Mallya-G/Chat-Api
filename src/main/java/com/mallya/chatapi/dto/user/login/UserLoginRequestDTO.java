package com.mallya.chatapi.dto.user.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginRequestDTO {
    @Email(message = "Invalid email")
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_=+\\-;':></?{}]).{8,30}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be 8-30 characters long.")
    private String password;
}
