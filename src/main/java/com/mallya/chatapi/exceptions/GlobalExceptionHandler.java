package com.mallya.chatapi.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> handleRuntimeException(RuntimeException ex){
        Map<String, String > errors = new HashMap<>();
        errors.put("error" , ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Map<String,String>> handleUserException(UserException ex){
        Map<String, String > errors = new HashMap<>();
        errors.put("error" , ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(FriendRequestException.class)
    public ResponseEntity<Map<String,String>> handleFriendRequestException(FriendRequestException ex){
        Map<String, String > errors = new HashMap<>();
        errors.put("error" , ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConversationException.class)
    public ResponseEntity<Map<String,String>> handleConversationException(ConversationException ex){
        Map<String, String > errors = new HashMap<>();
        errors.put("error" , ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }
}
