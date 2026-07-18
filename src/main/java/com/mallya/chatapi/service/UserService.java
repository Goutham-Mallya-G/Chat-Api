package com.mallya.chatapi.service;

import com.mallya.chatapi.dto.user.UpdatePasswordRequestDTO;
import com.mallya.chatapi.dto.user.UserResponseDTO;
import com.mallya.chatapi.dto.user.UserUpdateRequestDTO;
import com.mallya.chatapi.dto.util.UtilDTO;
import com.mallya.chatapi.exceptions.UserException;
import com.mallya.chatapi.model.Users;
import com.mallya.chatapi.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UtilDTO utilDTO;

    public UserResponseDTO getUser(String email) {
        Users user = getUserByEmail(email);
        return utilDTO.convertUserToUserResponseDTO(user);
    }

    public UserResponseDTO updateUser(@Valid UserUpdateRequestDTO requestDTO, String email) {
        Users user = getUserByEmail(email);

        if(requestDTO.getName() != null){
            user.setName(requestDTO.getName());
        }

        if(requestDTO.getAbout() != null){
            user.setAbout(requestDTO.getAbout());
        }

        if(requestDTO.getProfilePic() != null){
            user.setProfilePic(requestDTO.getProfilePic());
        }

        return utilDTO.convertUserToUserResponseDTO(userRepository.save(user));
    }

    public Map<String, String> changeUserPassword(@Valid UpdatePasswordRequestDTO requestDTO, String email) {
        Users user = getUserByEmail(email);
        if(!passwordEncoder.matches(requestDTO.getCurrentPassword(), user.getPassword())){
            throw new UserException("Invalid current password");

        }
        if(passwordEncoder.matches(requestDTO.getNewPassword(), user.getPassword())){
            throw new UserException("New password cannot be the same as old password");
        }

        String newHashedPassword = passwordEncoder.encode(requestDTO.getNewPassword());
        user.setPassword(newHashedPassword);
        userRepository.save(user);

        return Map.of("Message","Password updated");


    }

    public Map<String, String> deleteUser(String email) {
        Users user = getUserByEmail(email);
        userRepository.delete(user);
        return Map.of("Message","User deleted");
    }

    private Users getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException("No user found"));
    }
}
