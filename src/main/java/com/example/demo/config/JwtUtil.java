package com.example.demo.config;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import java.util.*;

public class JwtUtil {

    private final String secret;
    private final long expiration;

    public JwtUtil(String secret, long expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }

    public String generateToken(User u) {
        return Jwts.builder()
                .claim("userId", u.getId())
                .claim("email", u.getEmail())
                .claim("role", u.getRole().name())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Jws<Claims> validateAndParse(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token);
    }
}
