package com.example.demo.config;

import com.example.demo.model.AppUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component   // ✅ MAKES IT A SPRING BEAN
public class JwtUtil {

    private final Key key;
    private final long expirationMs;

    // REQUIRED BY TEST / DEFAULT
    public JwtUtil() {
        this.key = Keys.hmacShaKeyFor(
                "01234567890123456789012345678901".getBytes()
        );
        this.expirationMs = 3600000;
    }

    // OPTIONAL CONSTRUCTOR
    public JwtUtil(String secret, int expirationMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationMs;
    }

    // REQUIRED BY TEST
    public String generateToken(AppUser user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // REQUIRED BY TEST
    public Jws<Claims> validateAndParse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    // USED BY FILTER
    public String extractUsername(String token) {
        return validateAndParse(token).getBody().getSubject();
    }
}
