package com.mallya.chatapi.controller;

import com.mallya.chatapi.dto.user.UpdatePasswordRequestDTO;
import com.mallya.chatapi.dto.user.UserResponseDTO;
import com.mallya.chatapi.dto.user.UserUpdateRequestDTO;
import com.mallya.chatapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getUser(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(userService.getUser(userDetails.getUsername()));
    }

    @PutMapping("/profile")
    public ResponseEntity<UserResponseDTO> updateUser(@Valid @RequestBody UserUpdateRequestDTO requestDTO, @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(userService.updateUser(requestDTO,userDetails.getUsername()));
    }

    @PatchMapping("/password")
    public ResponseEntity<Map<String,String>> updatePassword(@Valid @RequestBody UpdatePasswordRequestDTO requestDTO, @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(userService.changeUserPassword(requestDTO, userDetails.getUsername()));
    }

    @DeleteMapping("")
    public ResponseEntity<Map<String ,String>> deleteUser(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(userService.deleteUser(userDetails.getUsername()));
    }


}
