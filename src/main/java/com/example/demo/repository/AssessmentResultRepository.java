package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssessmentResultRepository
        extends JpaRepository<AssessmentResult, Long> {

    // ✅ matches entity field EXACTLY
    List<AssessmentResult> findByStudentProfileId(Long studentProfileId);

    List<AssessmentResult> findBySkillId(Long skillId);
}
