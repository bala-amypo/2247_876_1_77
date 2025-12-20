package com.example.demo.controller;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.serviceimpl.UserServiceImpl;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(
            @Valid @RequestBody RegisterRequest request) {

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        User savedUser = userService.register(user);

        UserDTO dto = new UserDTO();
        dto.setId(savedUser.getId());
        dto.setFullName(savedUser.getFullName());
        dto.setEmail(savedUser.getEmail());
        dto.setRole(savedUser.getRole());
        dto.setCreatedAt(savedUser.getCreatedAt());

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
