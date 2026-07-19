package com.mallya.chatapi.dto.conversation;

import com.mallya.chatapi.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ConversationResponseDTO {
    private Long conversationId;

    private Long friendId;

    private String friendName;

    private String profilePicture;
}
