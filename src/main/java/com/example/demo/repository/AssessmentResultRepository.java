// package com.example.demo.repository;

// import com.example.demo.entity.AssessmentResult;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.time.Instant;
// import java.util.List;

// public interface AssessmentResultRepository
//         extends JpaRepository<AssessmentResult, Long> {

//     // REQUIRED BY TESTS
//     List<AssessmentResult>
//     findByStudentProfileIdAndSkillId(Long studentProfileId, Long skillId);

//     // REQUIRED BY TESTS
//     List<AssessmentResult>
//     findRecentByStudent(Long studentProfileId);

//     // REQUIRED BY TESTS
//     Double avgScoreByCohortAndSkill(String cohort, Long skillId);

//     // REQUIRED BY TESTS
//     List<AssessmentResult>
//     findResultsForStudentBetween(Long studentProfileId,
//                                  Instant start,
//                                  Instant end);
//}
package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {

    // Used by services/tests
    List<AssessmentResult> findByStudentProfileIdAndSkillId(
            Long studentProfileId,
            Long skillId
    );

    // ---------- FIX 1 ----------
    // Correct JPA-derived method
    List<AssessmentResult> findByStudentProfileIdAndAttemptedAtBetween(
            Long studentProfileId,
            Instant from,
            Instant to
    );

    // ---------- FIX 2 ----------
    // Compatibility for existing tests
    default List<AssessmentResult> findResultsForStudentBetween(
            Long studentProfileId,
            Instant from,
            Instant to
    ) {
        return findByStudentProfileIdAndAttemptedAtBetween(studentProfileId, from, to);
    }

    // ---------- FIX 3 ----------
    List<AssessmentResult> findRecentByStudentProfileId(Long studentProfileId);

    default List<AssessmentResult> findRecentByStudent(Long studentId) {
        return findRecentByStudentProfileId(studentId);
    }

    // ---------- FIX 4 ----------
    Double avgScoreByCohortAndSkill(String cohort, Long skillId);
}
