package com.mallya.chatapi.service;

import com.mallya.chatapi.dto.user.login.UserLoginRequestDTO;
import com.mallya.chatapi.dto.user.register.UserRegisterRequestDTO;
import com.mallya.chatapi.exceptions.UserException;
import com.mallya.chatapi.model.User;
import com.mallya.chatapi.repository.UserRepository;
import com.mallya.chatapi.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public Map<String,String> registerUser(@Valid UserRegisterRequestDTO requestDTO) {
        if (userRepository.existsByEmailOrUsername(requestDTO.getEmail(), requestDTO.getUsername())) {
            throw new UserException("Email or Username is already registered");
        }

        User user = new User();
        user.setName(requestDTO.getName());
        user.setEmail(requestDTO.getEmail());
        user.setUsername(requestDTO.getUsername());
        if(requestDTO.getAbout() != null && !requestDTO.getAbout().isBlank()){
            user.setAbout(requestDTO.getAbout());
        }
        if(requestDTO.getAbout() != null && !requestDTO.getAbout().isBlank()){
            user.setProfilePic(requestDTO.getProfilePic());
        }
        String hashedPassword = passwordEncoder.encode(requestDTO.getPassword());
        user.setPassword(hashedPassword);

        userRepository.save(user);

        return Map.of("Message","User Registered Successfully");
    }

    public Map<String, String> loginUser(@Valid UserLoginRequestDTO requestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getEmail(),requestDTO.getPassword()));
        String token = jwtService.generateToken((UserDetails) authentication.getPrincipal());
        return Map.of("Message",token);
    }
}
