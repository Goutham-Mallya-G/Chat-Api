package com.mallya.chatapi.controller;

import com.mallya.chatapi.dto.friends.FriendRequestResponseDTO;
import com.mallya.chatapi.dto.friends.FriendResponseDTO;
import com.mallya.chatapi.service.FriendRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendsController {

    private final FriendRequestService friendRequestService;

    @PostMapping("/request/{id}")
    public ResponseEntity<FriendRequestResponseDTO> friendRequest(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(friendRequestService.friendRequest(id, userDetails.getUsername()));
    }

    @PutMapping("/request/{id}/accept")
    public ResponseEntity<FriendRequestResponseDTO> acceptRequest(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(friendRequestService.acceptRequest(id, userDetails.getUsername()));
    }

    @PutMapping("/request/{id}/reject")
    public ResponseEntity<FriendRequestResponseDTO> rejectRequest(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(friendRequestService.rejectRequest(id, userDetails.getUsername()));
    }

    @DeleteMapping("/request/{id}/cancel")
    public ResponseEntity<FriendRequestResponseDTO> cancelRequest(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(friendRequestService.cancelRequest(id, userDetails.getUsername()));
    }

    @GetMapping("")
    public ResponseEntity<List<FriendResponseDTO>> getFriends(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(friendRequestService.getAllFriends(userDetails.getUsername()));
    }

    @GetMapping("/requests")
    public ResponseEntity<List<FriendRequestResponseDTO>> getPendingRequests(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(friendRequestService.getPendingRequests(userDetails.getUsername()));
    }

}
