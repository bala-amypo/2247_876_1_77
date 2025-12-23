package com.example.demo.repository;

import com.example.demo.entity.SkillGapRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillGapRecommendationRepository
        extends JpaRepository<SkillGapRecommendation, Long> {

    // REQUIRED by tests
    List<SkillGapRecommendation>
    findByStudentProfileIdOrderByGeneratedAtDesc(Long studentProfileId);
}
