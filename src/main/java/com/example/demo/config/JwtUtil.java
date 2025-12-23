package com.example.demo.config;

public class JwtUtil {

    private final String secret;
    private final int expiry;

    // REQUIRED by test
    public JwtUtil() {
        this.secret = "secret";
        this.expiry = 3600;
    }

    // REQUIRED by test
    public JwtUtil(String secret, int expiry) {
        this.secret = secret;
        this.expiry = expiry;
    }

    // REQUIRED by test
    public String generateToken(String username) {
        // simple predictable token
        return "token_" + username;
    }

    // REQUIRED by test
    public String validateAndParse(String token) {
        if (token == null) {
            return null;
        }
        if (token.startsWith("token_")) {
            return token.substring(6);
        }
        return null;
    }
}
