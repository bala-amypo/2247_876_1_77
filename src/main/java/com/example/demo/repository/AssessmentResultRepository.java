package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface AssessmentResultRepository
        extends JpaRepository<AssessmentResult, Long> {

    // REQUIRED BY TESTS
    List<AssessmentResult>
    findByStudentProfileIdAndSkillId(Long studentProfileId, Long skillId);

    // REQUIRED BY TESTS
    List<AssessmentResult>
    findRecentByStudent(Long studentProfileId);

    // REQUIRED BY TESTS
    Double avgScoreByCohortAndSkill(String cohort, Long skillId);

    // REQUIRED BY TESTS
    List<AssessmentResult>
    findResultsForStudentBetween(Long studentProfileId,
                                 Instant start,
                                 Instant end);
}
