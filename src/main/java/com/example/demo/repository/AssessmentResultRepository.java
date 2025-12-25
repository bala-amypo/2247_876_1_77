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
// }

package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface AssessmentResultRepository
        extends JpaRepository<AssessmentResult, Long> {

    // REQUIRED BY TESTS
    List<AssessmentResult> findByStudentProfileId(Long studentProfileId);

    // REQUIRED BY TESTS
    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long studentProfileId, Long skillId);

    // REQUIRED BY TESTS
    List<AssessmentResult> findRecentByStudent(Long studentProfileId);

    // REQUIRED BY TESTS
    List<AssessmentResult> findResultsForStudentBetween(
            Long studentProfileId,
            Instant start,
            Instant end
    );

    // REQUIRED BY TESTS
    @Query("""
        SELECT AVG(ar.score)
        FROM AssessmentResult ar
        JOIN StudentProfile sp ON ar.studentProfileId = sp.id
        WHERE sp.cohort = :cohort AND ar.skillId = :skillId
    """)
    Double avgScoreByCohortAndSkill(String cohort, Long skillId);
}
