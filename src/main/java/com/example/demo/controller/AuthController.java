// package com.example.demo.controller;

// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     @PostMapping("/login")
//     public String login() {
//         return "OK";
//     }

//     @PostMapping("/register")
//     public String register() {
//         return "OK";
//     }
// }
// 2 package com.example.demo.controller;

// import com.example.demo.dto.LoginRequest;
// import com.example.demo.dto.RegisterRequest;
// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     private final UserRepository userRepository;

//     public AuthController(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     // ---------- REGISTER ----------
//     @PostMapping("/register")
//     public ResponseEntity<User> register(@RequestBody RegisterRequest request) {

//         User user = new User();
//         user.setEmail(request.getEmail());
//         user.setPassword(request.getPassword());
//         user.setFullName(request.getFullName());

//         // handle role safely
//         if (request.getRole() != null) {
//             user.setRole(User.Role.valueOf(request.getRole().toUpperCase()));
//         } else {
//             user.setRole(User.Role.STUDENT);
//         }

//         return ResponseEntity.ok(userRepository.save(user));
//     }

//     // ---------- LOGIN ----------
//     @PostMapping("/login")
//     public ResponseEntity<User> login(@RequestBody LoginRequest request) {

//         User user = userRepository.findByEmail(request.getEmail())
//                 .orElseThrow(() -> new RuntimeException("Invalid credentials"));

//         if (!user.getPassword().equals(request.getPassword())) {
//             throw new RuntimeException("Invalid credentials");
//         }

//         return ResponseEntity.ok(user);
//     }

// } // ✅ THIS CLOSING BRACE WAS MISSING EARLIER
package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*") // fixes Swagger "Failed to fetch"
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // ---------------- REGISTER ----------------
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(User.Role.valueOf(request.getRole().toUpperCase()))
                .createdAt(Instant.now())
                .build();

        return ResponseEntity.ok(userService.save(user));
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest request) {

        User user = userService.findByEmailAndPassword(
                request.getEmail(),
                request.getPassword()
        );

        return ResponseEntity.ok(user);
    }
}
