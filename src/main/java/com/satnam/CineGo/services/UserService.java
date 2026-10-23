package com.satnam.CineGo.services;

import com.satnam.CineGo.dtos.requestDTOs.CreateUserRequest;
import com.satnam.CineGo.dtos.responseDTOs.UserResponse;
import com.satnam.CineGo.enums.Role;
import com.satnam.CineGo.model.User;
import com.satnam.CineGo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse signup(CreateUserRequest createUserRequest){

        User user = new User();
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user.setEmail(createUserRequest.getEmail());
        user.setRole(createUserRequest.getRole());

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .role(savedUser.getRole())
                .email(savedUser.getEmail())
                .username(savedUser.getUsername())
                .userId(savedUser.getId())
                .build();
    }
}
