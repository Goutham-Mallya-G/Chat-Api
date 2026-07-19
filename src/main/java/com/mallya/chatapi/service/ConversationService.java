package com.mallya.chatapi.service;

import com.mallya.chatapi.dto.conversation.ConversationResponseDTO;
import com.mallya.chatapi.dto.util.UtilDTO;
import com.mallya.chatapi.enums.Status;
import com.mallya.chatapi.exceptions.ConversationException;
import com.mallya.chatapi.exceptions.FriendRequestException;
import com.mallya.chatapi.exceptions.UserException;
import com.mallya.chatapi.model.Conversation;
import com.mallya.chatapi.model.FriendRequest;
import com.mallya.chatapi.model.User;
import com.mallya.chatapi.repository.ConversationRepository;
import com.mallya.chatapi.repository.FriendRequestRepository;
import com.mallya.chatapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final FriendRequestRepository friendRequestRepository;
    private final UtilDTO utilDTO;

    public ConversationResponseDTO createOrGetConversation(Long friendId, String userEmail){

        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new UserException("No user found with this id"));

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserException("Please Login again"));

        if (user.getId().equals(friend.getId())) {
            throw new IllegalArgumentException("You cannot start a conversation with yourself");
        }

        FriendRequest friendRequest = friendRequestRepository.findFriendship(friend,user)
                .orElseThrow(() -> new FriendRequestException("No connection established"));

        if(!friendRequest.getStatus().equals(Status.ACCEPTED)){
            throw new FriendRequestException("You are not friends");
        }

        Optional<Conversation> conversation = conversationRepository.findOneToOneConversation(user,friend);

        if(conversation.isEmpty()){
            Conversation newConversation = new Conversation();
            newConversation.setParticipants(List.of(user,friend));
            return utilDTO.convertConversationToConversationResponseDTO(conversationRepository.save(newConversation),friend);
        }

        return utilDTO.convertConversationToConversationResponseDTO(conversation.get(),friend);
    }

    public Conversation getAuthorizedConversation(Long conversationId, User user) {

        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ConversationException("Conversation not found"));

        boolean participant = conversation.getParticipants()
                .stream()
                .anyMatch(p -> p.getId().equals(user.getId()));

        if (!participant) {
            throw new ConversationException("Unauthorized");
        }

        return conversation;
    }

    public List<ConversationResponseDTO> getConversations(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException("Login again"));

        List<Conversation> conversations = conversationRepository.findByParticipantsContaining(user);

        List<ConversationResponseDTO> response = new ArrayList<>();

        for(Conversation conversation : conversations){
            User friend = conversation.getParticipants()
                    .stream()
                    .filter(u -> !u.getId().equals(user.getId()))
                    .findFirst()
                    .orElseThrow();
            response.add(utilDTO.convertConversationToConversationResponseDTO(conversation,friend));
        }
        return response;
    }
}
