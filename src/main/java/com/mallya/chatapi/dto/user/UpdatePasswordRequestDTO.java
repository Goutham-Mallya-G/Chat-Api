package com.mallya.chatapi.dto.user;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdatePasswordRequestDTO {
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_=+\\-;':></?{}]).{8,30}$",
            message = "Old password is in incorrect format")
    private String currentPassword;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_=+\\-;':></?{}]).{8,30}$",
            message = "New Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be 8-30 characters long.")
    private String newPassword;
}
