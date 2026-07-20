package com.mallya.chatapi.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateRequestDTO {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 30, message = "Name should be under 3 to 30 characters")
    private String name;
    @Size(max = 50, message = "About cannot exceed more than 50 characters")
    private String about;
    private String profilePic;
}
