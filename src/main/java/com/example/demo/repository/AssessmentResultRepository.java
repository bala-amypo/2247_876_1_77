// package com.example.demo.repository;

// import com.example.demo.entity.AssessmentResult;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

// import java.time.Instant;
// import java.util.List;

// public interface AssessmentResultRepository
//         extends JpaRepository<AssessmentResult, Long> {

//     List<AssessmentResult> findByStudentProfileId(Long studentProfileId);

//     List<AssessmentResult> findBySkillId(Long skillId);

//     List<AssessmentResult> findByStudentProfileIdAndSkillId(
//             Long studentProfileId,
//             Long skillId
//     );

//     // ⭐ REQUIRED BY TESTS (mocked)
//     Double avgScoreByCohortAndSkill(String cohort, Long skillId);

//     // ⭐ REQUIRED BY TESTS (mocked)
//     List<AssessmentResult> findRecentByStudent(Long studentProfileId);

//     // ⭐ REQUIRED BY TESTS (mocked)
//     List<AssessmentResult> findResultsForStudentBetween(
//             Long studentProfileId,
//             Instant start,
//             Instant end
//     );
//}
package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {

    // t050, t017
    Optional<AssessmentResult> findByStudentProfileIdAndSkillId(Long studentProfileId, Long skillId);

    // t015
    List<AssessmentResult> findRecentByStudentProfileId(Long studentProfileId);

    // t038, t060
    List<AssessmentResult> findResultsForStudentBetween(
            Long studentProfileId,
            Instant start,
            Instant end
    );

    // t060
    @Query("""
        SELECT AVG(ar.score)
        FROM AssessmentResult ar
        WHERE ar.studentProfile.cohort = :cohort
        AND ar.skill.id = :skillId
    """)
    Double avgScoreByCohortAndSkill(String cohort, Long skillId);
}
