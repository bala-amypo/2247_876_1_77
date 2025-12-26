
// package com.example.demo.controller;

// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/students")
// public class StudentProfileController {
// }
package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-profiles")
public class StudentProfileController {

    private final StudentProfileService studentProfileService;

    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    @PostMapping
    public StudentProfile create(@RequestBody StudentProfile profile) {
        return studentProfileService.save(profile);
    }

    @PutMapping("/{id}")
    public StudentProfile update(
            @PathVariable Long id,
            @RequestBody StudentProfile profile) {
        return studentProfileService.update(id, profile);
    }

    @GetMapping("/{id}")
    public StudentProfile getById(@PathVariable Long id) {
        return studentProfileService.getById(id);
    }

    @GetMapping
    public List<StudentProfile> getAll() {
        return studentProfileService.getAll();
    }
}
