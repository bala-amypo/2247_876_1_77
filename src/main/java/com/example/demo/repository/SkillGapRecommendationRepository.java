// package com.example.demo.repository;

// import com.example.demo.entity.SkillGapRecommendation;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.List;

// public interface SkillGapRecommendationRepository
//         extends JpaRepository<SkillGapRecommendation, Long> {

//     // REQUIRED BY TESTS
//     List<SkillGapRecommendation>
//     findByStudentProfileIdOrderByGeneratedAtDesc(Long studentProfileId);

//     // ⭐ REQUIRED BY TEST t038
//     List<SkillGapRecommendation> findByStudentOrdered(Long studentProfileId);
// }

package com.example.demo.repository;

import com.example.demo.entity.SkillGapRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillGapRecommendationRepository
        extends JpaRepository<SkillGapRecommendation, Long> {

    // REQUIRED BY TESTS (used directly)
    List<SkillGapRecommendation>
    findByStudentProfileIdOrderByGeneratedAtDesc(Long studentProfileId);

    // REQUIRED BY TEST t038
    default List<SkillGapRecommendation> findByStudentOrdered(Long studentId) {
        return findByStudentProfileIdOrderByGeneratedAtDesc(studentId);
    }
}
