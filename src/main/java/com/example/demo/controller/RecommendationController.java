package com.example.demo.controller;

import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService service;

    public RecommendationController(RecommendationService service) {
        this.service = service;
    }

    @PostMapping("/{studentId}/{skillId}")
    public SkillGapRecommendation computeOne(
            @PathVariable Long studentId,
            @PathVariable Long skillId) {

        return service.computeRecommendationForStudentSkill(studentId, skillId);
    }

    @GetMapping("/{studentId}")
    public List<SkillGapRecommendation> getForStudent(
            @PathVariable Long studentId) {

        return service.getRecommendationsForStudent(studentId);
    }
}
