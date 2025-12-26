// package com.example.demo.config;

// import com.example.demo.entity.User;
// import io.jsonwebtoken.*;
// import java.util.*;

// public class JwtUtil {

//     private final String secret;
//     private final long expiration;

//     public JwtUtil(String secret, long expiration) {
//         this.secret = secret;
//         this.expiration = expiration;
//     }

//     public String generateToken(User u) {
//         return Jwts.builder()
//                 .claim("userId", u.getId())
//                 .claim("email", u.getEmail())
//                 .claim("role", u.getRole().name())
//                 .setExpiration(new Date(System.currentTimeMillis() + expiration))
//                 .signWith(SignatureAlgorithm.HS256, secret)
//                 .compact();
//     }

//     public Jws<Claims> validateAndParse(String token) {
//         return Jwts.parser()
//                 .setSigningKey(secret)
//                 .parseClaimsJws(token);
//     }
// }
package com.example.demo.config;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private final Key key;
    private final long validity;

    public JwtUtil(String secret, long validity) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.validity = validity;
    }

    public String generateToken(User u) {
        return Jwts.builder()
                .claim("userId", u.getId())
                .claim("email", u.getEmail())
                .claim("role", u.getRole().name())
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(key)
                .compact();
    }

    public Jws<Claims> validateAndParse(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }
}
