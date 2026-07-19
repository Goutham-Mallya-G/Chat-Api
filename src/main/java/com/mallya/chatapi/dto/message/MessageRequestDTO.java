package com.mallya.chatapi.dto.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MessageRequestDTO {
    private Long conversationId;
    @NotBlank
    @Size(max = 300)
    private String content;
}
