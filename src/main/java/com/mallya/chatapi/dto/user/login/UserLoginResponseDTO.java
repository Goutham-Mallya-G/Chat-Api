package com.mallya.chatapi.dto.user.login;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginResponseDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
}
