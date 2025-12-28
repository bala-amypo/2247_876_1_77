

// package com.example.demo.controller;

// import com.example.demo.entity.User;
// import com.example.demo.service.UserService;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import com.example.demo.dto.LoginRequest;


// @RestController
// @RequestMapping("/users")
// public class AuthController {

//     private final UserService userService;

//     public AuthController(UserService userService) {
//         this.userService = userService;
//     }

//     // ✅ CREATE USER
//     @PostMapping
//     public ResponseEntity<User> createUser(@RequestBody User user) {
//         return ResponseEntity.ok(userService.register(user));
//     }

//     // ✅ GET USER BY ID
//     @GetMapping("/{id}")
//     public ResponseEntity<User> getUser(@PathVariable Long id) {
//         return ResponseEntity.ok(userService.getById(id));
//     }

//     // ✅ GET INSTRUCTORS
//     @GetMapping("/instructors")
//     public ResponseEntity<?> getInstructors() {
//         return ResponseEntity.ok(userService.listInstructors());
//     }

//     // ✅ SIMPLE LOGIN (NO TOKEN, SERVICE-SAFE)
//    @PostMapping("/login")
//     public ResponseEntity<User> login(@RequestBody LoginRequest request) {

//     User user = userService.findByEmail(request.getEmail());

//     // optional validation
//     if (!user.getFullName().equals(request.getFullName())) {
//         return ResponseEntity.status(401).build();
//     }

//     return ResponseEntity.ok(user);
// }

// }
package com.example.demo.controller;

import com.example.demo.config.JwtUtil;
import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository userRepo, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {

        User user = User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(encoder.encode(request.getPassword())) // ✅ NOT NULL
                .role(User.Role.valueOf(request.getRole()))
                .createdAt(Instant.now())
                .build();

        return userRepo.save(user);
    }

    // ✅ LOGIN + TOKEN
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token);
    }
}
