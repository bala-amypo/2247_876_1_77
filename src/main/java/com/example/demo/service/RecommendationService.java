// package com.example.demo.service;

// import com.example.demo.entity.SkillGapRecommendation;
// import java.util.List;

// public interface RecommendationService {

//     SkillGapRecommendation computeRecommendationForStudentSkill(
//             Long studentProfileId,
//             Long skillId
//     );

//     List<SkillGapRecommendation> computeRecommendationsForStudent(
//             Long studentProfileId
//     );

//     List<SkillGapRecommendation> getRecommendationsForStudent(
//             Long studentProfileId
//     );
// }
package com.example.demo.service;

import com.example.demo.entity.SkillGapRecommendation;

import java.util.List;

public interface RecommendationService {

    // used by controller
    SkillGapRecommendation computeRecommendationForStudentSkill(
            Long studentId,
            Long skillId
    );

    // used by tests
    List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId);

    // used by history endpoint + tests
    List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId);
}
