package com.mallya.chatapi.controller;

import com.mallya.chatapi.dto.user.login.UserLoginRequestDTO;
import com.mallya.chatapi.dto.user.register.UserRegisterRequestDTO;
import com.mallya.chatapi.service.AuthenticationService;
import com.mallya.chatapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerUser(@Valid @RequestBody UserRegisterRequestDTO requestDTO){
        return ResponseEntity.ok(authenticationService.registerUser(requestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> loginUser(@Valid @RequestBody UserLoginRequestDTO requestDTO){
        return ResponseEntity.ok(authenticationService.loginUser(requestDTO));
    }
}
