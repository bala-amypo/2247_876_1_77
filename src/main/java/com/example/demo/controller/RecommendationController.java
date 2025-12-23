package com.example.demo.controller;

import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService service;

    public RecommendationController(RecommendationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SkillGapRecommendation> createRecommendation(
            @RequestBody SkillGapRecommendation recommendation) {
        return ResponseEntity.ok(
                service.createRecommendation(recommendation)
        );
    }

    @GetMapping
    public ResponseEntity<List<SkillGapRecommendation>> getAllRecommendations() {
        return ResponseEntity.ok(
                service.getAllRecommendations()
        );
    }
}
