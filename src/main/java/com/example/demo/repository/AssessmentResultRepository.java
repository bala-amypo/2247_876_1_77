
// package com.example.demo.repository;

// import com.example.demo.entity.AssessmentResult;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.time.Instant;
// import java.util.List;

// public interface AssessmentResultRepository
//         extends JpaRepository<AssessmentResult, Long> {

//     List<AssessmentResult> findByStudentProfileIdAndSkillId(
//             Long studentProfileId,
//             Long skillId
//     );

//     Double avgScoreByCohortAndSkill(String cohort, Long skillId);

//     List<AssessmentResult> findRecentByStudent(Long studentId);

//     List<AssessmentResult> findResultsForStudentBetween(
//             Long studentId,
//             Instant from,
//             Instant to
//     );
// }
package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface AssessmentResultRepository
        extends JpaRepository<AssessmentResult, Long> {

    List<AssessmentResult> findByStudentProfileIdAndSkillId(
            Long studentProfileId,
            Long skillId
    );

    // ✅ FIX: Explicit JPQL to avoid method-name parsing
    @Query("""
           SELECT AVG(r.score)
           FROM AssessmentResult r
           WHERE r.studentProfile.cohort = :cohort
             AND r.skill.id = :skillId
           """)
    Double avgScoreByCohortAndSkill(
            @Param("cohort") String cohort,
            @Param("skillId") Long skillId
    );

    List<AssessmentResult> findRecentByStudent(Long studentId);

    List<AssessmentResult> findResultsForStudentBetween(
            Long studentId,
            Instant from,
            Instant to
    );
}
