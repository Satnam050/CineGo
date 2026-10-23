package com.satnam.CineGo.controller;


import com.satnam.CineGo.dtos.requestDTOs.CreateUserRequest;
import com.satnam.CineGo.dtos.requestDTOs.LoginDetails;
import com.satnam.CineGo.dtos.responseDTOs.UserResponse;
import com.satnam.CineGo.enums.Role;
import com.satnam.CineGo.jwt.JwtUtils;
import com.satnam.CineGo.model.User;
import com.satnam.CineGo.repository.UserRepository;
import com.satnam.CineGo.services.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserRepository userRepository, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils=jwtUtils;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody CreateUserRequest createUserRequest){
        if(!userRepository.findByUsername(createUserRequest.getUsername()).isEmpty())throw new RuntimeException("User already exists");
        createUserRequest.setRole(Role.USER);
        return userService.signup(createUserRequest);
    }

    @PostMapping("/admin/signup")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse adminSignup(@RequestBody CreateUserRequest createUserRequest){
        if(!userRepository.findByUsername(createUserRequest.getUsername()).isEmpty())throw new RuntimeException("User already exists");
        createUserRequest.setRole(createUserRequest.getRole());
        return userService.signup(createUserRequest);
    }

    @PostMapping("/login")
    public String generateToken(@RequestBody LoginDetails loginDetails){
        try {
            System.out.println(loginDetails.getUsername()+ loginDetails.getPassword());
            authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(loginDetails.getUsername(), loginDetails.getPassword()));
            return jwtUtils.generateToken(loginDetails.getUsername());

        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }


    }
}
