package com.mallya.chatapi.service;

import com.mallya.chatapi.dto.user.login.UserLoginResponseDTO;
import com.mallya.chatapi.dto.user.register.UserRegisterRequestDTO;
import com.mallya.chatapi.dto.user.register.UserRegisterResponseDTO;
import com.mallya.chatapi.dto.util.UtilDTO;
import com.mallya.chatapi.exceptions.UserException;
import com.mallya.chatapi.model.Users;
import com.mallya.chatapi.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UtilDTO utilDTO;

    public UserLoginResponseDTO getUser(String email) {
        Users user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("No user found"));
        return utilDTO.convertUserToUserResponseDTO(user);
    }
}
