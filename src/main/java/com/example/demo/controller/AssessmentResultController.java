package com.example.demo.controller;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.serviceimpl.AssessmentResultServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assessments")
public class AssessmentResultController {

    private final AssessmentResultServiceImpl service;

    // ✅ constructor name is correct
    public AssessmentResultController(AssessmentResultServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public AssessmentResult save(@RequestBody AssessmentResult result) {
        return service.save(result);
    }

    @GetMapping("/student/{id}")
    public List<AssessmentResult> getByStudent(@PathVariable Long id) {
        return service.findRecentByStudent(id);
    }
}
