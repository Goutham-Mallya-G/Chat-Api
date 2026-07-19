package com.mallya.chatapi.repository;

import com.mallya.chatapi.model.FriendRequest;
import com.mallya.chatapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    Optional<FriendRequest> findBySenderAndReceiver(User sender, User receiver);

    @Query("""
    select fr
     from FriendRequest fr
     where (fr.sender = :user or fr.receiver = :user)
     and fr.status = 'ACCEPTED'
""")
    List<FriendRequest> findFriends(User user);

    @Query("""
    select fr
     from FriendRequest fr
     where (fr.receiver = :user)
     and fr.status = 'PENDING'
""")
    List<FriendRequest> findPendingRequests(User user);

    boolean existsBySenderAndReceiverOrReceiverAndSender(
            User sender1,
            User receiver1,
            User receiver2,
            User sender2
    );

    @Query("""
        SELECT f
        FROM FriendRequest f
        WHERE (
            f.sender = :user1
            AND f.receiver = :user2
        )
        OR (
            f.sender = :user2
            AND f.receiver = :user1
        )
""")
    Optional<FriendRequest> findFriendship(User user1, User user2);
}
