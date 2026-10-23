package com.satnam.CineGo.dtos.requestDTOs;


import com.satnam.CineGo.enums.Role;
import lombok.Data;

@Data
public class CreateUserRequest {

    private String username;
    private String password;
    private String email;
    private Role role;
}
