
// package com.example.demo.config;

// import com.example.demo.entity.User;
// import io.jsonwebtoken.*;
// import io.jsonwebtoken.security.Keys;
// import java.security.Key;
// import java.util.Date;

// public class JwtUtil {

//     private final Key key;
//     private final long validity;

//     public JwtUtil(String secret, long validity) {
//         this.key = Keys.hmacShaKeyFor(secret.getBytes());
//         this.validity = validity;
//     }

//     public String generateToken(User u) {
//         return Jwts.builder()
//                 .claim("userId", u.getId())
//                 .claim("email", u.getEmail())
//                 .claim("role", u.getRole().name())
//                 .setExpiration(new Date(System.currentTimeMillis() + validity))
//                 .signWith(key)
//                 .compact();
//     }

//     public Jws<Claims> validateAndParse(String token) {
//         return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//     }
// }
package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private SecretKey secretKey;
    private long expirationMillis;

    // ✅ REQUIRED by Spring
    public JwtUtil() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        this.expirationMillis = 1000 * 60 * 60; // 1 hour
    }

    // ✅ REQUIRED by TestNG (THIS FIXES YOUR ERROR)
    public JwtUtil(String secret, int expirationSeconds) {
        this.secretKey = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
        this.expirationMillis = expirationSeconds * 1000L;
    }

    // ✅ REQUIRED by tests
    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(secretKey)
                .compact();
    }

    // ✅ REQUIRED by tests
    public Jws<Claims> validateAndParse(String token) {
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build();

        return parser.parseClaimsJws(token);
    }
}

