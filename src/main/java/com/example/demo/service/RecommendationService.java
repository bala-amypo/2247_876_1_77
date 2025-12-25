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

    SkillGapRecommendation createRecommendation(SkillGapRecommendation recommendation);

    List<SkillGapRecommendation> getAllRecommendations();

    List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId);

    void computeRecommendationsForStudent(Long studentId);

    SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId);
}
