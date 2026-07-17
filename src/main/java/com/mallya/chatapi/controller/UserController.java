package com.mallya.chatapi.controller;

import com.mallya.chatapi.dto.user.login.UserLoginResponseDTO;
import com.mallya.chatapi.dto.user.register.UserRegisterRequestDTO;
import com.mallya.chatapi.dto.user.register.UserRegisterResponseDTO;
import com.mallya.chatapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserLoginResponseDTO> getUser(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(userService.getUser(userDetails.getUsername()));
    }


}
