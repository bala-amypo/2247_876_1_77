// package com.example.demo.repository;

// import com.example.demo.entity.SkillGapRecommendation;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.List;

// public interface SkillGapRecommendationRepository
//         extends JpaRepository<SkillGapRecommendation, Long> {

//     // REQUIRED BY TESTS
//     List<SkillGapRecommendation>
//     findByStudentOrdered(Long studentProfileId);
//}
package com.example.demo.repository;

import com.example.demo.entity.SkillGapRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillGapRecommendationRepository
        extends JpaRepository<SkillGapRecommendation, Long> {

    // --------------------------------------------------
    // FIXED: Explicit query replaces broken method name
    // --------------------------------------------------
    @Query("""
        SELECT s
        FROM SkillGapRecommendation s
        WHERE s.student.id = ?1
        ORDER BY s.gapScore DESC
    """)
    List<SkillGapRecommendation> findByStudentOrdered(Long studentId);
}
