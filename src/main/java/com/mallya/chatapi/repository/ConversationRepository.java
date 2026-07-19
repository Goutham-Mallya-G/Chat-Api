package com.mallya.chatapi.repository;

import com.mallya.chatapi.model.Conversation;
import com.mallya.chatapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query("""
        SELECT c
        FROM Conversation c
        WHERE SIZE(c.participants) = 2
            AND :user1 MEMBER OF c.participants
            AND :user2 MEMBER OF c.participants
""")
    Optional<Conversation> findOneToOneConversation(User user1, User user2);

    List<Conversation> findByParticipantsContaining(User user);

}
