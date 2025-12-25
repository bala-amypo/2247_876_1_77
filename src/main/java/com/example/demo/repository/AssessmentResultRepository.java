package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssessmentResultRepository
        extends JpaRepository<AssessmentResult, Long> {

    // ✅ USED BY SERVICES
    List<AssessmentResult> findByStudentProfileId(Long studentProfileId);

    // ✅ IF TEST EXPECTS "recent", order explicitly
    List<AssessmentResult>
    findByStudentProfileIdOrderByAttemptedAtDesc(Long studentProfileId);
}
