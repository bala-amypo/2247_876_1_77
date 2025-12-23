package com.example.demo.controller;

import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.serviceimpl.RecommendationServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationServiceImpl service;

    public RecommendationController(RecommendationServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<SkillGapRecommendation> findAll() {
        return service.findAll();
    }
}
