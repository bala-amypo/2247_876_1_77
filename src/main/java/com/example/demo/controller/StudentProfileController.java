package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {

    private final StudentProfileService studentProfileService;

    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    // POST /api/students
    @PostMapping
    public ResponseEntity<StudentProfile> createProfile(
            @RequestBody StudentProfile profile) {

        return ResponseEntity.ok(
                studentProfileService.createProfile(profile)
        );
    }

    // GET /api/students/{id}
    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                studentProfileService.getById(id)
        );
    }

    // GET /api/students/enrollment/{id}
    @GetMapping("/enrollment/{id}")
    public ResponseEntity<StudentProfile> getByEnrollment(
            @PathVariable String id) {

        return ResponseEntity.ok(
                studentProfileService.getByEnrollmentId(id)
        );
    }

    // GET /api/students
    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAll() {
        return ResponseEntity.ok(
                studentProfileService.getAll()
        );
    }
}
