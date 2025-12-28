
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

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long EXPIRATION = 1000 * 60 * 60 * 24;

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key)
                .compact();
    }
}
