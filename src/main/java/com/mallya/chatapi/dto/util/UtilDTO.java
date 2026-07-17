package com.mallya.chatapi.dto.util;

import com.mallya.chatapi.dto.user.login.UserLoginResponseDTO;
import com.mallya.chatapi.model.Users;
import org.springframework.stereotype.Component;

@Component
public class UtilDTO {
    public UserLoginResponseDTO convertUserToUserResponseDTO(Users user){
        UserLoginResponseDTO responseDTO = new UserLoginResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setName(user.getName());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setEmail(user.getEmail());
        return responseDTO;
    }
}
