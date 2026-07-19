package com.mallya.chatapi.repository;

import com.mallya.chatapi.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmailOrUsername(@Email(message = "Invalid email") String email, @NotNull(message = "Username cannot be null") @NotBlank(message = "Username cannot be blank") @Size(min = 3, max = 30, message = "Name should be under 3 to 30 characters") String username);
}
