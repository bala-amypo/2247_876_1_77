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

    // Used by recommendation service & tests
    List<AssessmentResult> findByStudentProfileIdAndSkillId(
            Long studentProfileId,
            Long skillId
    );

    // Actual JPA-derived query (runtime-safe)
    List<AssessmentResult> findRecentByStudentProfileId(Long studentProfileId);

    // ✅ Compatibility method for existing TestNG tests
    default List<AssessmentResult> findRecentByStudent(Long studentId) {
        return findRecentByStudentProfileId(studentId);
    }

    // Used in HQL/JPQL test cases
    Double avgScoreByCohortAndSkill(String cohort, Long skillId);

    // Time-range query
    List<AssessmentResult> findResultsForStudentBetween(
            Long studentProfileId,
            Instant from,
            Instant to
    );
}
