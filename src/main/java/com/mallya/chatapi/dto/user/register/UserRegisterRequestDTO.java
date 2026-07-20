package com.mallya.chatapi.dto.user.register;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterRequestDTO {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 30, message = "Name should be under 3 to 30 characters")
    private String name;
    @Email(message = "Invalid email")
    private String email;
    @NotNull(message = "Username cannot be null")
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 30, message = "Name should be under 3 to 30 characters")
    private String username;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_=+\\-;':></?{}]).{8,30}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be 8-30 characters long.")
    private String password;
    private String profilePic;
    @Size(max = 50, message = "About cannot exceed more than 50 characters")
    private String about;

}
