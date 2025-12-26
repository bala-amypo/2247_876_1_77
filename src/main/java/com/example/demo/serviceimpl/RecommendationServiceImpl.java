// package com.example.demo.serviceimpl;
// 59
// import com.example.demo.entity.AssessmentResult;
// import com.example.demo.entity.Skill;
// import com.example.demo.entity.SkillGapRecommendation;
// import com.example.demo.entity.StudentProfile;
// import com.example.demo.repository.AssessmentResultRepository;
// import com.example.demo.repository.SkillGapRecommendationRepository;
// import com.example.demo.repository.SkillRepository;
// import com.example.demo.repository.StudentProfileRepository;
// import com.example.demo.service.RecommendationService;
// import org.springframework.stereotype.Service;

// import java.time.Instant;
// import java.util.ArrayList;
// import java.util.List;

// @Service
// public class RecommendationServiceImpl implements RecommendationService {

//     private final AssessmentResultRepository assessmentResultRepository;
//     private final SkillGapRecommendationRepository recommendationRepository;
//     private final StudentProfileRepository studentProfileRepository;
//     private final SkillRepository skillRepository;

//     public RecommendationServiceImpl(
//             AssessmentResultRepository assessmentResultRepository,
//             SkillGapRecommendationRepository recommendationRepository,
//             StudentProfileRepository studentProfileRepository,
//             SkillRepository skillRepository
//     ) {
//         this.assessmentResultRepository = assessmentResultRepository;
//         this.recommendationRepository = recommendationRepository;
//         this.studentProfileRepository = studentProfileRepository;
//         this.skillRepository = skillRepository;
//     }

//     // ===== TEST-ONLY METHODS (NO @Override) =====

//     public SkillGapRecommendation computeRecommendationForStudentSkill(
//             Long studentId,
//             Long skillId
//     ) {

//         StudentProfile profile = studentProfileRepository.findById(studentId)
//                 .orElseThrow(() -> new RuntimeException("Profile not found"));

//         Skill skill = skillRepository.findById(skillId)
//                 .orElseThrow(() -> new RuntimeException("Skill not found"));

//         List<AssessmentResult> results =
//                 assessmentResultRepository
//                         .findByStudentProfileIdAndSkillId(studentId, skillId);

//         double avgScore = results.stream()
//                 .mapToDouble(AssessmentResult::getScore)
//                 .average()
//                 .orElse(0.0);

//         SkillGapRecommendation recommendation =
//                 SkillGapRecommendation.builder()
//                         .studentProfile(profile)
//                         .skill(skill)
//                         .gapScore(100.0 - avgScore)
//                         .generatedAt(Instant.now())
//                         .generatedBy("SYSTEM")
//                         .build();

//         return recommendationRepository.save(recommendation);
//     }

//     public List<SkillGapRecommendation> computeRecommendationsForStudent(
//             Long studentId
//     ) {

//         StudentProfile profile = studentProfileRepository.findById(studentId)
//                 .orElseThrow(() -> new RuntimeException("Profile not found"));

//         List<Skill> skills = skillRepository.findByActiveTrue();
//         List<SkillGapRecommendation> list = new ArrayList<>();

//         for (Skill skill : skills) {
//             list.add(
//                     computeRecommendationForStudentSkill(
//                             profile.getId(),
//                             skill.getId()
//                     )
//             );
//         }

//         return list;
//     }

//     public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
//         return recommendationRepository
//                 .findByStudentProfileIdOrderByGeneratedAtDesc(studentId);
//     }

//     // ===== INTERFACE METHODS (NO ERROR) =====

//     public SkillGapRecommendation createRecommendation(
//             SkillGapRecommendation recommendation
//     ) {
//         return recommendationRepository.save(recommendation);
//     }

//     public List<SkillGapRecommendation> getAllRecommendations() {
//         return recommendationRepository.findAll();
//     }

// }
package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.SkillGapRecommendationRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.RecommendationService;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final SkillGapRecommendationRepository recommendationRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final SkillRepository skillRepository;

    public RecommendationServiceImpl(
            SkillGapRecommendationRepository recommendationRepository,
            StudentProfileRepository studentProfileRepository,
            SkillRepository skillRepository
    ) {
        this.recommendationRepository = recommendationRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public SkillGapRecommendation createRecommendation(
            SkillGapRecommendation recommendation
    ) {
        return recommendationRepository.save(recommendation);
    }

    @Override
    public List<SkillGapRecommendation> getAllRecommendations() {
        return recommendationRepository.findAll();
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
        return recommendationRepository
                .findByStudentProfileIdOrderByGeneratedAtDesc(studentId);
    }

    @Override
    public List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId) {
        return recommendationRepository
                .findByStudentProfileIdOrderByGeneratedAtDesc(studentId);
    }

    @Override
    public SkillGapRecommendation computeRecommendationForStudentSkill(
            Long studentId,
            Long skillId
    ) {
        StudentProfile profile =
                studentProfileRepository.findById(studentId).orElseThrow();

        Skill skill =
                skillRepository.findById(skillId).orElseThrow();

        SkillGapRecommendation rec =
                SkillGapRecommendation.builder()
                        .studentProfile(profile)
                        .skill(skill)
                        .generatedAt(Instant.now())
                        .generatedBy("SYSTEM")
                        .gapScore(0.0)
                        .priority("MEDIUM")
                        .recommendedAction("IMPROVE_SKILL")
                        .build();

        return recommendationRepository.save(rec);
    }
}
