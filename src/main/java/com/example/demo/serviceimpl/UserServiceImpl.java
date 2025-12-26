package com.example.demo.serviceimpl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.*;

public class UserServiceImpl {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    public User register(User u) {
        if (repo.existsByEmail(u.getEmail()))
            throw new IllegalArgumentException("Email already exists");
        u.setPassword(encoder.encode(u.getPassword()));
        return repo.save(u);
    }

    public User findByEmail(String email) {
        return repo.findByEmail(email).orElseThrow();
    }

    public User getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> listInstructors() {
        return repo.findByRole(User.Role.INSTRUCTOR);
    }
}
