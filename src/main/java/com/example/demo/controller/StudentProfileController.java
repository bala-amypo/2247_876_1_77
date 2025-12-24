package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class StudentProfileController {

    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    @PostMapping
    public StudentProfile save(@RequestBody StudentProfile profile) {
        return service.createOrUpdateProfile(profile);
    }

    @GetMapping("/user/{userId}")
    public StudentProfile getByUser(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }
}
