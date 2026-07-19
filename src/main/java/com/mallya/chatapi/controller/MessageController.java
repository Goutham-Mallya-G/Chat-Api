package com.mallya.chatapi.controller;


import com.mallya.chatapi.dto.message.MessageRequestDTO;
import com.mallya.chatapi.dto.message.MessageResponseDTO;
import com.mallya.chatapi.model.Message;
import com.mallya.chatapi.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/")
    public ResponseEntity<MessageResponseDTO> sendMessage(@Valid @RequestBody MessageRequestDTO messageRequestDTO, @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(messageService.sendMessage(messageRequestDTO,userDetails.getUsername()));
    }

    @GetMapping("{conversationId}/messages")
    public ResponseEntity<Page<MessageResponseDTO>> getMessages(@PathVariable Long conversationId, @RequestParam int page, @RequestParam int size, @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(messageService.getMessages(conversationId,page,size,userDetails.getUsername()));
    }
}
