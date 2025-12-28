

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

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepo, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {

        if (userRepo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setRole(User.Role.valueOf(request.getRole()));

        return userRepo.save(user);
    }

    // ✅ LOGIN + TOKEN
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getFullName().equals(request.getFullName())) {
            throw new RuntimeException("Invalid name");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token);
    }
}
