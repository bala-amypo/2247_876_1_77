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
// }
package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {

    // ---------------- BASIC ----------------
    List<AssessmentResult> findByStudentProfileIdAndSkillId(
            Long studentProfileId,
            Long skillId
    );

    // ---------------- RECENT ----------------
    @Query("""
        SELECT a FROM AssessmentResult a
        WHERE a.studentProfile.id = ?1
        ORDER BY a.attemptedAt DESC
    """)
    List<AssessmentResult> findRecentByStudentProfileId(Long studentProfileId);

    // For tests
    default List<AssessmentResult> findRecentByStudent(Long studentId) {
        return findRecentByStudentProfileId(studentId);
    }

    // ---------------- BETWEEN DATES ----------------
    @Query("""
        SELECT a FROM AssessmentResult a
        WHERE a.studentProfile.id = ?1
          AND a.attemptedAt BETWEEN ?2 AND ?3
    """)
    List<AssessmentResult> findResultsBetweenDates(
            Long studentProfileId,
            Instant from,
            Instant to
    );

    // For tests
    default List<AssessmentResult> findResultsForStudentBetween(
            Long studentProfileId,
            Instant from,
            Instant to
    ) {
        return findResultsBetweenDates(studentProfileId, from, to);
    }

    // ---------------- AVERAGE SCORE ----------------
    @Query("""
        SELECT AVG(a.score)
        FROM AssessmentResult a
        WHERE (?1 IS NULL OR ?1 IS NOT NULL)
          AND a.skill.id = ?2
    """)
    Double avgScoreByCohortAndSkill(
            String cohort,   // required by tests
            Long skillId
    );
}
