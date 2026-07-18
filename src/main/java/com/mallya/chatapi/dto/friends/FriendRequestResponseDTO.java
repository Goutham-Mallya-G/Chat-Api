package com.mallya.chatapi.dto.friends;

import com.mallya.chatapi.enums.Status;
import com.mallya.chatapi.model.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FriendRequestResponseDTO {
    private Users sender;
    private Users receiver;
    private Status status;
}
