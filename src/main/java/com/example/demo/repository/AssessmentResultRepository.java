package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssessmentResultRepository
        extends JpaRepository<AssessmentResult, Long> {

    // REQUIRED by tests
    List<AssessmentResult> findRecentByStudent(Long studentProfileId);

    // REQUIRED by tests
    Optional<AssessmentResult>
    findByStudentProfileIdAndSkillId(Long studentProfileId, Long skillId);
}
