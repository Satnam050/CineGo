package com.satnam.CineGo.jwt;

import com.satnam.CineGo.dtos.requestDTOs.LoginDetails;
import com.satnam.CineGo.model.User;
import com.satnam.CineGo.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.util.Date;

@Service
public class JwtUtils {

    private final long EXPIRATION_TIME = 1000*60*60;
    private static final String SECRET = "my-secret-key-for-jwt-this-is-a-big-secret-now@@@@@##!!!!";
    private static final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }

    public static boolean validateToken(String username, UserDetails userDetails, String token) {

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

    public static String extractUsername(String token){
        return extractClaims(token).getSubject();
    }


    public static Claims extractClaims(String token){
        Claims payload = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return payload;

    }
}
