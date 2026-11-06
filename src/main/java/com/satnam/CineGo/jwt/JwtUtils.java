package com.satnam.CineGo.jwt;

import com.satnam.CineGo.dtos.requestDTOs.LoginDetails;
import com.satnam.CineGo.model.User;
import com.satnam.CineGo.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.util.Date;

@Service
public class JwtUtils {

    private final long EXPIRATION_TIME = 1000*60*60;
    private final SecretKey key;

    public JwtUtils(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    public  boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String username, UserDetails userDetails, String token) {

        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }


    public String generateToken(String username){

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    public  String extractUsername(String token){
        return extractClaims(token).getSubject();
    }


    public  Claims extractClaims(String token){
        Claims payload = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return payload;

    }
}
