package com.mallya.chatapi.websocket;

import com.mallya.chatapi.dto.message.MessageRequestDTO;
import com.mallya.chatapi.dto.message.MessageResponseDTO;
import com.mallya.chatapi.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;

    @MessageMapping("/chat.send")
    public void sendMessage(@Payload MessageRequestDTO requestDTO,Principal principal){

        MessageResponseDTO response = messageService.sendMessage(requestDTO, principal.getName());

        simpMessagingTemplate.convertAndSend("/topic/conversations/"+response.getConversationID(), response);

    }

}
