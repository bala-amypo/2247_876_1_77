package com.example.demo.repository;

import com.example.demo.entity.SkillGapRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillGapRecommendationRepository
        extends JpaRepository<SkillGapRecommendation, Long> {

    // ✅ USED BY SERVICE
    List<SkillGapRecommendation>
    findByStudentProfileIdOrderByGeneratedAtDesc(Long studentProfileId);

    // ✅ USED BY TESTS (THIS WAS MISSING)
    List<SkillGapRecommendation>
    findByStudentOrdered(Long studentProfileId);
}
