package com.mallya.chatapi.dto.util;

import com.mallya.chatapi.dto.conversation.ConversationResponseDTO;
import com.mallya.chatapi.dto.friends.FriendRequestResponseDTO;
import com.mallya.chatapi.dto.friends.FriendResponseDTO;
import com.mallya.chatapi.dto.message.MessageResponseDTO;
import com.mallya.chatapi.dto.user.UserResponseDTO;
import com.mallya.chatapi.model.Conversation;
import com.mallya.chatapi.model.FriendRequest;
import com.mallya.chatapi.model.Message;
import com.mallya.chatapi.model.User;
import org.springframework.stereotype.Component;

@Component
public class UtilDTO {
    public UserResponseDTO convertUserToUserResponseDTO(User user){
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setName(user.getName());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setProfilePic(user.getProfilePic());
        responseDTO.setAbout(user.getAbout());
        responseDTO.setCreatedAt(user.getCreatedAt());
        responseDTO.setUpdatedAt(user.getUpdatedAt());
        responseDTO.setLastSeen(user.getLastSeen());
        return responseDTO;
    }

    public FriendRequestResponseDTO convertFriendRequestToFriendRequestResponseDTO(FriendRequest friendRequest){
        FriendRequestResponseDTO responseDTO = new FriendRequestResponseDTO();
        responseDTO.setSender(friendRequest.getSender());
        responseDTO.setReceiver(friendRequest.getReceiver());
        responseDTO.setStatus(friendRequest.getStatus());
        return responseDTO;
    }

    public FriendResponseDTO convertUserToFriendResponseDTO(User friend){
        FriendResponseDTO responseDTO = new FriendResponseDTO();
        responseDTO.setId(friend.getId());
        responseDTO.setUsername(friend.getUsername());
        responseDTO.setEmail(friend.getEmail());
        responseDTO.setAbout(friend.getAbout());
        responseDTO.setProfilePic(friend.getProfilePic());
        responseDTO.setName(friend.getName());
        return responseDTO;
    }

    public ConversationResponseDTO convertConversationToConversationResponseDTO(Conversation conversation, User friend){
        ConversationResponseDTO responseDTO = new ConversationResponseDTO();
        responseDTO.setConversationId(conversation.getId());
        responseDTO.setFriendId(friend.getId());
        responseDTO.setFriendName(friend.getName());
        responseDTO.setProfilePicture(friend.getProfilePic());
        return responseDTO;
    }

    public MessageResponseDTO convertMessageToMessageResponseDTO(Message message){
        MessageResponseDTO responseDTO = new MessageResponseDTO();
        responseDTO.setId(message.getId());
        responseDTO.setContent(message.getContent());
        responseDTO.setSenderName(message.getSender().getName());
        responseDTO.setCreatedAt(message.getCreatedAt());
        return responseDTO;
    }
}
