package com.mallya.chatapi.dto.util;

import com.mallya.chatapi.dto.user.UserResponseDTO;
import com.mallya.chatapi.model.Users;
import org.springframework.stereotype.Component;

@Component
public class UtilDTO {
    public UserResponseDTO convertUserToUserResponseDTO(Users user){
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setName(user.getName());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setProfilePic(user.getProfilePic());
        responseDTO.setAbout(user.getAbout());
        responseDTO.setCreatedAt(user.getCreatedAt());
        responseDTO.setUpdatedAt(user.getUpdatedAt());
        responseDTO.setLastSeen(user.getLastSeen());
        return responseDTO;
    }
}
