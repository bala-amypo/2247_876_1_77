package com.example.demo.controller;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.serviceimpl.AssessmentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assessments")
public class AssessmentResultController {

    private final AssessmentServiceImpl service;

    // ✅ CONSTRUCTOR NAME MUST MATCH CLASS NAME
    public AssessmentResultController(AssessmentServiceImpl service) {
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
