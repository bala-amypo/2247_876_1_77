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
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillGapRecommendationRepository
        extends JpaRepository<SkillGapRecommendation, Long> {

    // ✅ REQUIRED BY RecommendationServiceImpl & TESTS
    @Query("""
        SELECT r
        FROM SkillGapRecommendation r
        WHERE r.student.id = ?1
        ORDER BY r.priority DESC
    """)
    List<SkillGapRecommendation> findByStudentOrdered(Long studentId);
}
