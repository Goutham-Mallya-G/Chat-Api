package com.mallya.chatapi.service;

import com.mallya.chatapi.dto.friends.FriendRequestResponseDTO;
import com.mallya.chatapi.dto.friends.FriendResponseDTO;
import com.mallya.chatapi.dto.util.UtilDTO;
import com.mallya.chatapi.enums.Status;
import com.mallya.chatapi.exceptions.FriendRequestException;
import com.mallya.chatapi.exceptions.UserException;
import com.mallya.chatapi.model.FriendRequest;
import com.mallya.chatapi.model.Users;
import com.mallya.chatapi.repository.FriendRequestRepository;
import com.mallya.chatapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;
    private final UtilDTO utilDto;

    public FriendRequestResponseDTO friendRequest(Long id, String email) {
        FriendRequest friendRequest = new FriendRequest();

        Users sender = getUserByEmail(email);
        Users receiver = getUserById(id);

        if(sender.equals(receiver)){
            throw new FriendRequestException("Cannot send request to yourself");
        }

        if(friendRequestRepository.existsBySenderAndReceiverOrReceiverAndSender(sender,receiver,receiver,sender)){
            throw new FriendRequestException("Duplicate request not acceptable");
        }

        friendRequest.setSender(sender);
        friendRequest.setReceiver(receiver);
        friendRequest.setStatus(Status.PENDING);

        friendRequestRepository.save(friendRequest);
        return utilDto.convertFriendRequestToFriendRequestResponseDTO(friendRequest);
    }

    public FriendRequestResponseDTO acceptRequest(Long id, String email) {
        Users sender = getUserById(id);
        Users receiver = getUserByEmail(email);

        FriendRequest friendRequest = friendRequestRepository.findBySenderAndReceiver(sender,receiver)
                                    .orElseThrow(() -> new FriendRequestException("No request found"));

        if(friendRequest.getStatus() != Status.PENDING){
            throw new FriendRequestException("Request is no longer pending");
        }
        friendRequest.setStatus(Status.ACCEPTED);
        friendRequestRepository.save(friendRequest);
        return utilDto.convertFriendRequestToFriendRequestResponseDTO(friendRequest);
    }

    public FriendRequestResponseDTO rejectRequest(Long id, String email) {
        Users sender = getUserById(id);
        Users receiver = getUserByEmail(email);

        FriendRequest friendRequest = friendRequestRepository.findBySenderAndReceiver(sender,receiver)
                .orElseThrow(() -> new FriendRequestException("No request found"));

        if(friendRequest.getStatus() != Status.PENDING){
            throw new FriendRequestException("Request is no longer pending");
        }

        friendRequest.setStatus(Status.REJECTED);
        friendRequestRepository.delete(friendRequest);
        return utilDto.convertFriendRequestToFriendRequestResponseDTO(friendRequest);
    }


    public FriendRequestResponseDTO cancelRequest(Long id, String email) {
        Users sender = getUserByEmail(email);
        Users receiver = getUserById(id);

        FriendRequest friendRequest = friendRequestRepository.findBySenderAndReceiver(sender,receiver)
                .orElseThrow(() -> new FriendRequestException("No request found"));

        if(friendRequest.getStatus() != Status.PENDING){
            throw new FriendRequestException("Request is no longer pending");
        }

        friendRequest.setStatus(Status.CANCELLED);
        friendRequestRepository.delete(friendRequest);
        return utilDto.convertFriendRequestToFriendRequestResponseDTO(friendRequest);
    }

    public List<FriendResponseDTO> getAllFriends(String email) {
        Users user = getUserByEmail(email);
        List<FriendResponseDTO> list = new ArrayList<>();
        List<FriendRequest> friendsList = friendRequestRepository.findFriends(user);
        for(FriendRequest friendRequest : friendsList){
            if(!friendRequest.getSender().equals(user)){
                list.add(utilDto.convertUserToFriendResponseDTO(friendRequest.getSender()));
            }else if(!friendRequest.getReceiver().equals(user)){
                list.add(utilDto.convertUserToFriendResponseDTO(friendRequest.getReceiver()));

            }
        }
        return list;
    }

    public List<FriendRequestResponseDTO> getPendingRequests(String email) {
        Users user = getUserByEmail(email);
        List<FriendRequestResponseDTO> list = new ArrayList<>();
        List<FriendRequest> friendsList = friendRequestRepository.findPendingRequests(user);
        for(FriendRequest friendRequest : friendsList){
            list.add(utilDto.convertFriendRequestToFriendRequestResponseDTO(friendRequest));
        }
        return list;
    }

    private @NonNull Users getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException("No user found"));
    }

    private @NonNull Users getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException("No user found"));
    }

}
