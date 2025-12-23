package com.example.demo.service;

import com.example.demo.entity.SkillGapRecommendation;
import java.util.List;

public interface RecommendationService {

    SkillGapRecommendation createRecommendation(SkillGapRecommendation recommendation);

    List<SkillGapRecommendation> getAllRecommendations();
}
