package com.mallya.chatapi.repository;

import com.mallya.chatapi.model.FriendRequest;
import com.mallya.chatapi.model.Users;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    Optional<FriendRequest> findBySenderAndReceiver(Users sender, Users receiver);

    @Query("""
    select fr
     from FriendRequest fr
     where (fr.sender = :user or fr.receiver = :user)
     and fr.status = 'ACCEPTED'
""")
    List<FriendRequest> findFriends(Users user);

    @Query("""
    select fr
     from FriendRequest fr
     where (fr.receiver = :user)
     and fr.status = 'PENDING'
""")
    List<FriendRequest> findPendingRequests(Users user);

    boolean existsBySenderAndReceiverOrReceiverAndSender(
            Users sender1,
            Users receiver1,
            Users receiver2,
            Users sender2
    );
}
