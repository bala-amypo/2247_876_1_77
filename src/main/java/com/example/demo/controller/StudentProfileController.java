package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentProfileController {

    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StudentProfile> createProfile(
            @RequestBody StudentProfile profile) {
        return ResponseEntity.ok(service.createProfile(profile));
    }

    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAllProfiles() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/enrollment/{enrollmentId}")
    public ResponseEntity<StudentProfile> getByEnrollment(
            @PathVariable String enrollmentId) {
        return ResponseEntity.ok(service.getByEnrollmentId(enrollmentId));
    }
}
