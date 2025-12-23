package com.example.demo.serviceimpl;

import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.repository.SkillGapRecommendationRepository;
import com.example.demo.repository.SkillGapRecordRepository;

import java.util.List;

public class RecommendationServiceImpl {

    private final SkillGapRecordRepository gapRepository;
    private final SkillGapRecommendationRepository recommendationRepository;

    public RecommendationServiceImpl(
            SkillGapRecordRepository gapRepository,
            SkillGapRecommendationRepository recommendationRepository) {
        this.gapRepository = gapRepository;
        this.recommendationRepository = recommendationRepository;
    }

    public SkillGapRecommendation save(SkillGapRecommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    public List<SkillGapRecommendation> findAll() {
        return recommendationRepository.findAll();
    }
}
