package com.example.demo.config;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public String generateToken(String username) {
        return "dummy-token-for-" + username;
    }

    public String extractUsername(String token) {
        return token.replace("dummy-token-for-", "");
    }
}
