package com.example.demo.serviceimpl;

import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.repository.SkillGapRecommendationRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final SkillGapRecommendationRepository recommendationRepository;

    public RecommendationServiceImpl(
            SkillGapRecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public SkillGapRecommendation createRecommendation(
            SkillGapRecommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    @Override
    public List<SkillGapRecommendation> getAllRecommendations() {
        return recommendationRepository.findAll();
    }
}
