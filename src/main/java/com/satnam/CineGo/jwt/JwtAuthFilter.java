package com.satnam.CineGo.jwt;

import com.satnam.CineGo.services.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {


    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthFilter(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {



        String header = request.getHeader("Authorization");

        String token = null;
        String username = null;

        if(header!=null && header.startsWith("Bearer ")){
             token = header.substring(7);
            username = JwtUtils.extractUsername(token);
        }

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            if(JwtUtils.validateToken(username, userDetails, token)){

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(request);
                SecurityContextHolder.getContext().setAuthentication(authToken);

                System.out.println("Authenticated: " +
                        SecurityContextHolder.getContext()
                                .getAuthentication().isAuthenticated());

                System.out.println("Principal: " +
                        SecurityContextHolder.getContext()
                                .getAuthentication().getPrincipal());

                System.out.println("Authorities: " +
                        SecurityContextHolder.getContext()
                                .getAuthentication().getAuthorities());
                System.out.println(authToken.getDetails());
            }
        }


        filterChain.doFilter(request,response);
    }
}
