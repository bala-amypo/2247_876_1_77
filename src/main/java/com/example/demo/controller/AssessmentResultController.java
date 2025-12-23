package com.example.demo.controller;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.serviceimpl.AssessmentResultServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentResultController {

    private final AssessmentResultServiceImpl service;

    public AssessmentResultController(AssessmentResultServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public AssessmentResult create(@RequestBody AssessmentResult result) {
        return service.save(result);
    }

    @GetMapping("/student/{studentId}")
    public List<AssessmentResult> getByStudent(@PathVariable Long studentId) {
        return service.findByStudent(studentId);
    }

    @GetMapping("/skill/{skillId}")
    public List<AssessmentResult> getBySkill(@PathVariable Long skillId) {
        return service.findBySkill(skillId);
    }
}
