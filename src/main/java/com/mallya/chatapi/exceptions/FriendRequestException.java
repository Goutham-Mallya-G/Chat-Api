package com.mallya.chatapi.exceptions;

public class FriendRequestException extends  RuntimeException{
    public FriendRequestException(String message){
        super(message);
    }
}
