package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.serviceimpl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return service.save(user);
    }

    @GetMapping
    public List<User> findAll() {
        return service.findAll();
    }
}
