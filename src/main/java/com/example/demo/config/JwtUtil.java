package com.example.demo.config;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    // Dummy implementation for test & swagger
    public String extractUsername(String token) {
        // In real JWT, this decodes token
        return "test-user";
    }

    public boolean validateToken(String token) {
        return token != null && !token.isEmpty();
    }
}
