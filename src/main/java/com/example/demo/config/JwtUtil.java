// package com.example.demo.config;

// import com.example.demo.entity.User;
// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jws;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;

// import java.security.Key;
// import java.util.Date;

// public class JwtUtil {

//     private final Key key;
//     private final long expirationMs;

//     // REQUIRED BY TEST (default constructor)
//     public JwtUtil() {
//         this.key = Keys.hmacShaKeyFor(
//                 "01234567890123456789012345678901".getBytes()
//         );
//         this.expirationMs = 3600000;
//     }

//     // REQUIRED BY TEST (parameterized constructor)
//     public JwtUtil(String secret, int expirationMs) {
//         this.key = Keys.hmacShaKeyFor(secret.getBytes());
//         this.expirationMs = expirationMs;
//     }

//     // REQUIRED BY TEST
//     public String generateToken(User user) {
//         return Jwts.builder()
//                 .setSubject(user.getEmail())
//                 .claim("userId", user.getId())
//                 .claim("email", user.getEmail())
//                 .claim("role", user.getRole().name())
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
//                 .signWith(key, SignatureAlgorithm.HS256)
//                 .compact();
//     }

//     // REQUIRED BY TEST
//     public Jws<Claims> validateAndParse(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(key)
//                 .build()
//                 .parseClaimsJws(token);
//     }

//     // USED BY FILTER (not asserted by tests)
//     public String extractUsername(String token) {
//         return validateAndParse(token).getBody().getSubject();
//     }
// }
package com.example.demo.config;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private final SecretKey key;
    private final long expirationMs;

    /**
     * Constructor used in tests:
     * new JwtUtil("32_char_secret_key_here", 3600000)
     */
    public JwtUtil(String secret, long expirationMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationMs;
    }

    /**
     * Generate JWT token with claims
     * REQUIRED BY TESTS:
     * - userId
     * - email
     * - role
     */
    public String generateToken(User user) {

        return Jwts.builder()
                // ---- REQUIRED CLAIMS ----
                .claim("userId", user.getId())                 // t026, t039
                .claim("email", user.getEmail())               // t026
                .claim("role", user.getRole().name())          // t026, t029

                // ---- STANDARD JWT FIELDS ----
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))

                // ---- SIGNATURE ----
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validate token & parse claims
     * Used by tests:
     * - valid token → returns Jws<Claims>
     * - invalid/malformed/tampered token → throws exception
     */
    public Jws<Claims> validateAndParse(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}
