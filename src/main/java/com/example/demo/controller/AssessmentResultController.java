package com.example.demo.controller;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.service.AssessmentResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assessments")
public class AssessmentResultController {

    private final AssessmentResultService assessmentResultService;

    public AssessmentResultController(
            AssessmentResultService assessmentResultService) {
        this.assessmentResultService = assessmentResultService;
    }

    @PostMapping
    public ResponseEntity<AssessmentResult> createAssessment(
            @RequestBody AssessmentResult assessmentResult) {
        return ResponseEntity.ok(
                assessmentResultService.createAssessmentResult(assessmentResult)
        );
    }

    @GetMapping
    public ResponseEntity<List<AssessmentResult>> getAllAssessments() {
        return ResponseEntity.ok(
                assessmentResultService.getAllAssessmentResults()
        );
    }
}
