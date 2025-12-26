// // package com.example.demo.service;

// // import com.example.demo.entity.SkillGapRecommendation;
// // import java.util.List;

// // public interface RecommendationService {

// //     SkillGapRecommendation computeRecommendationForStudentSkill(
// //             Long studentProfileId,
// //             Long skillId
// //     );

// //     List<SkillGapRecommendation> computeRecommendationsForStudent(
// //             Long studentProfileId
// //     );

// //     List<SkillGapRecommendation> getRecommendationsForStudent(
// //             Long studentProfileId
// //     );
// // }
// package com.example.demo.service;

// import com.example.demo.entity.SkillGapRecommendation;

// import java.util.List;

// public interface RecommendationService {

//     SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId);

//     List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId);

//     List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId);
//}
package com.example.demo.service;

import com.example.demo.entity.SkillGapRecommendation;
import java.util.List;

public interface RecommendationService {
    SkillGapRecommendation computeRecommendationForStudentSkill(Long spId, Long skillId);
    List<SkillGapRecommendation> computeRecommendationsForStudent(Long spId);
    List<SkillGapRecommendation> getRecommendationsForStudent(Long id);
}
