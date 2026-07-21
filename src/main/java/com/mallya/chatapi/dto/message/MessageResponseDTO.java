package com.mallya.chatapi.dto.message;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageResponseDTO {

    private Long id;

    private String content;

    private String senderName;

    private LocalDateTime createdAt;

    private Long conversationID;
}
