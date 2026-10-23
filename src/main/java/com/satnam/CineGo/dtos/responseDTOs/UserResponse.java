package com.satnam.CineGo.dtos.responseDTOs;


import com.satnam.CineGo.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private String userId;
    private String username;
    private String email;
    private Role role;
}
