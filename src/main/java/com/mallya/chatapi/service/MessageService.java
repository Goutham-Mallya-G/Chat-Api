package com.mallya.chatapi.service;

import com.mallya.chatapi.dto.message.MessageRequestDTO;
import com.mallya.chatapi.dto.message.MessageResponseDTO;
import com.mallya.chatapi.dto.util.UtilDTO;
import com.mallya.chatapi.exceptions.ConversationException;
import com.mallya.chatapi.exceptions.UserException;
import com.mallya.chatapi.model.Conversation;
import com.mallya.chatapi.model.Message;
import com.mallya.chatapi.model.User;
import com.mallya.chatapi.repository.ConversationRepository;
import com.mallya.chatapi.repository.MessageRepository;
import com.mallya.chatapi.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final UtilDTO utilDTO;
    private final ConversationService conversationService;

    @Transactional
    public MessageResponseDTO sendMessage(@Valid MessageRequestDTO messageRequestDTO, String email) {
        User sender = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException("Login again"));

        Conversation conversation = conversationService.getAuthorizedConversation(messageRequestDTO.getConversationId(), sender);

        Message message = new Message();

        message.setConversation(conversation);
        message.setSender(sender);
        message.setContent(messageRequestDTO.getContent());

        return utilDTO.convertMessageToMessageResponseDTO(messageRepository.save(message));
    }

    public Page<MessageResponseDTO> getMessages(Long conversationId, int page, int size, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException("Login again"));

        conversationService.getAuthorizedConversation(conversationId, user);

        Pageable pageable = PageRequest.of(page,size);
        Page<Message> messagePage = messageRepository.findByConversationIdOrderByCreatedAtAsc(conversationId,pageable);
        return messagePage.map(utilDTO::convertMessageToMessageResponseDTO);
    }


}
