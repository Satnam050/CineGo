package com.satnam.CineGo.config;

import com.satnam.CineGo.enums.Permissions;
import com.satnam.CineGo.enums.Role;
import com.satnam.CineGo.jwt.JwtAuthFilter;
import com.satnam.CineGo.model.User;
import com.satnam.CineGo.repository.UserRepository;
import com.satnam.CineGo.services.CustomUserDetailsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http
                .csrf((csrf)->csrf.disable())
                .authorizeHttpRequests((auth)->
                        auth.requestMatchers("/api/auth/login", "/error", "/api/auth/signup").permitAll()
//                                .requestMatchers(HttpMethod.GET,"/api/shows/**").hasRole(Role.USER.name())
//                                .requestMatchers(HttpMethod.POST,"/api/shows/**").hasAuthority(Permissions.WRITE.name())
                                .anyRequest().authenticated())

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);



        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new CustomUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner commandLineRunner(PasswordEncoder passwordEncoder){
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                System.out.println("admin created");
                User user = new User();
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode("12345"));
                user.setRole(Role.ADMIN);

                userRepository.save(user);
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService ,PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(provider);

    }
}
