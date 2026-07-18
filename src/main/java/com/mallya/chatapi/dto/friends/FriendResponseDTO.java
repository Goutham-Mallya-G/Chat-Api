package com.mallya.chatapi.dto.friends;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FriendResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String profilePic;
    private String about;
}
