
// package com.example.demo.controller;

// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/students")
// public class StudentProfileController {
// }
package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentProfileController {

    private final StudentProfileService studentProfileService;

    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    @PostMapping
    public ResponseEntity<StudentProfile> create(@RequestBody StudentProfile profile) {
        return ResponseEntity.ok(studentProfileService.save(profile));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studentProfileService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAll() {
        return ResponseEntity.ok(studentProfileService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentProfile> update(
            @PathVariable Long id,
            @RequestBody StudentProfile profile) {
        return ResponseEntity.ok(studentProfileService.update(id, profile));
    }
}
