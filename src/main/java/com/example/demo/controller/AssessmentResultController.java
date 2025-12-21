package com.example.demo.controller;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.service.AssessmentResultService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentResultController {

    private final AssessmentResultService assessmentResultService;

    public AssessmentResultController(AssessmentResultService assessmentResultService) {
        this.assessmentResultService = assessmentResultService;
    }

    /**
     * Create a new assessment result
     */
    @PostMapping
    public ResponseEntity<AssessmentResult> createAssessment(
            @Valid @RequestBody AssessmentResult assessmentResult) {

        return ResponseEntity.ok(
                assessmentResultService.createAssessmentResult(assessmentResult)
        );
    }

    /**
     * Get all assessment results for a student
     */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AssessmentResult>> getByStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                assessmentResultService.getResultsByStudent(studentId)
        );
    }

    /**
     * Get all assessment results for a skill
     */
    @GetMapping("/skill/{skillId}")
    public ResponseEntity<List<AssessmentResult>> getBySkill(
            @PathVariable Long skillId) {

        return ResponseEntity.ok(
                assessmentResultService.getResultsBySkill(skillId)
        );
    }
}
