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




// package com.example.demo.controller;  2 imp swagger

// import com.example.demo.entity.User;
// import com.example.demo.service.UserService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/users")
// public class UserController {

//     private final UserService userService;

//     public UserController(UserService userService) {
//         this.userService = userService;
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<User> getUser(@PathVariable Long id) {
//         return ResponseEntity.ok(userService.getById(id));
//     }

//     @GetMapping("/instructors")
//     public ResponseEntity<List<User>> instructors() {
//         return ResponseEntity.ok(userService.listInstructors());
//     }
// }

// package com.example.demo.controller;

// import com.example.demo.entity.User;
// import com.example.demo.service.UserService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;



// import java.util.List;

// @RestController
// @RequestMapping("/users")
// public class AuthController {

//     private final UserService userService;

//     public AuthController(UserService userService) {
//         this.userService = userService;
//     }

//     // ✅ NEW POST METHOD (ONLY ADDITION)
//     @PostMapping
//     public ResponseEntity<User> createUser(@RequestBody User user) {
//         return ResponseEntity.ok(userService.register(user));
//     }

// }


//     // EXISTING METHODS (DO NOT TOUCH)
//     @GetMapping("/{id}")
//     public ResponseEntity<User> getById(@PathVariable Long id) {
//         return ResponseEntity.ok(userService.getById(id));
//     }

//     @GetMapping("/instructors")
//     public ResponseEntity<List<User>> getInstructors() {
//         return ResponseEntity.ok(userService.listInstructors());
//     }
// }

package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.LoginRequest;


@RestController
@RequestMapping("/users")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // ✅ CREATE USER
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    // ✅ GET USER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    // ✅ GET INSTRUCTORS
    @GetMapping("/instructors")
    public ResponseEntity<?> getInstructors() {
        return ResponseEntity.ok(userService.listInstructors());
    }

    // ✅ SIMPLE LOGIN (NO TOKEN, SERVICE-SAFE)
   @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest request) {

    User user = userService.findByEmail(request.getEmail());

    // optional validation
    if (!user.getFullName().equals(request.getFullName())) {
        return ResponseEntity.status(401).build();
    }

    return ResponseEntity.ok(user);
}

}
