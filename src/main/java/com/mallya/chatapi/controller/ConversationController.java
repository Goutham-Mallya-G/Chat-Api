package com.mallya.chatapi.controller;

import com.mallya.chatapi.dto.conversation.ConversationResponseDTO;
import com.mallya.chatapi.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conversations")
public class ConversationController {
    private final ConversationService conversationService;

    @PostMapping("/{id}")
    public ResponseEntity<ConversationResponseDTO> createOrGetConversation(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(conversationService.createOrGetConversation(id, userDetails.getUsername()));
    }

    @GetMapping("")
    public ResponseEntity<List<ConversationResponseDTO>> getConversations(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(conversationService.getConversations(userDetails.getUsername()));
    }
}
