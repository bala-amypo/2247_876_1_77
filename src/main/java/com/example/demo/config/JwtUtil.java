package com.example.demo.config;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    // ✅ REQUIRED BY TEST
    public JwtUtil() {}

    public String generateToken(String username) {
        // simple stub token
        return "token_" + username;
    }

    public String extractUsername(String token) {
        if (token == null) return null;
        if (token.startsWith("token_")) {
            return token.substring(6);
        }
        return null;
    }
}
