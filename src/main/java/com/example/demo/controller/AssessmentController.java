
// package com.example.demo.controller;

// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/assessments")
// public class AssessmentController {
// }
package com.example.demo.controller;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.service.AssessmentResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/assessments")
public class AssessmentController {

    private final AssessmentResultService assessmentResultService;

    public AssessmentController(AssessmentResultService assessmentResultService) {
        this.assessmentResultService = assessmentResultService;
    }

    @PostMapping
    public ResponseEntity<AssessmentResult> submit(
            @RequestBody AssessmentResult result) {
        return ResponseEntity.ok(assessmentResultService.save(result));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AssessmentResult>> getRecent(
            @PathVariable Long studentId) {
        return ResponseEntity.ok(
                assessmentResultService.getRecentResults(studentId)
        );
    }

    @GetMapping("/student/{studentId}/between")
    public ResponseEntity<List<AssessmentResult>> getBetween(
            @PathVariable Long studentId,
            @RequestParam Instant from,
            @RequestParam Instant to) {
        return ResponseEntity.ok(
                assessmentResultService.getResultsBetween(studentId, from, to)
        );
    }
}
