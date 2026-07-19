package com.mallya.chatapi.dto.friends;

import com.mallya.chatapi.enums.Status;
import com.mallya.chatapi.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FriendRequestResponseDTO {
    private User sender;
    private User receiver;
    private Status status;
}
