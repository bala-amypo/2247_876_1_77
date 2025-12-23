package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.serviceimpl.StudentProfileServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentProfileController {

    private final StudentProfileServiceImpl service;

    public StudentProfileController(StudentProfileServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public StudentProfile save(@RequestBody StudentProfile profile) {
        return service.save(profile);
    }

    @GetMapping("/{enrollmentId}")
    public StudentProfile findByEnrollmentId(@PathVariable String enrollmentId) {
        return service.findByEnrollmentId(enrollmentId);
    }
}
