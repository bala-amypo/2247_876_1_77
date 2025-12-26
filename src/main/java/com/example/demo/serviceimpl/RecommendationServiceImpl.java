// package com.example.demo.serviceimpl;

// import com.example.demo.entity.*;
// import com.example.demo.repository.*;
// import com.example.demo.service.RecommendationService;

// import java.util.ArrayList;
// import java.util.List;

// public class RecommendationServiceImpl implements RecommendationService {

//     private final AssessmentResultRepository assessmentRepo;
//     private final SkillGapRecommendationRepository recommendationRepo;
//     private final StudentProfileRepository profileRepo;
//     private final SkillRepository skillRepo;

//     public RecommendationServiceImpl(
//             AssessmentResultRepository assessmentRepo,
//             SkillGapRecommendationRepository recommendationRepo,
//             StudentProfileRepository profileRepo,
//             SkillRepository skillRepo) {

//         this.assessmentRepo = assessmentRepo;
//         this.recommendationRepo = recommendationRepo;
//         this.profileRepo = profileRepo;
//         this.skillRepo = skillRepo;
//     }

//     @Override
//     public SkillGapRecommendation computeRecommendationForStudentSkill(
//             Long studentProfileId, Long skillId) {

//         StudentProfile profile = profileRepo.findById(studentProfileId)
//                 .orElseThrow(() -> new RuntimeException("Profile not found"));

//         Skill skill = skillRepo.findById(skillId)
//                 .orElseThrow(() -> new RuntimeException("Skill not found"));

//         List<AssessmentResult> results =
//                 assessmentRepo.findByStudentProfileIdAndSkillId(studentProfileId, skillId);

//         double avgScore = results.stream()
//                 .mapToDouble(r -> r.getScore())
//                 .average()
//                 .orElse(0.0);

//         double gap = 100.0 - avgScore;

//         SkillGapRecommendation rec = SkillGapRecommendation.builder()
//                 .studentProfile(profile)
//                 .skill(skill)
//                 .gapScore(gap)
//                 .build();

//         return recommendationRepo.save(rec);
//     }

//     @Override
//     public List<SkillGapRecommendation> computeRecommendationsForStudent(
//             Long studentProfileId) {

//         StudentProfile profile = profileRepo.findById(studentProfileId)
//                 .orElseThrow(() -> new RuntimeException("Profile not found"));

//         List<Skill> skills = skillRepo.findByActiveTrue();
//         List<SkillGapRecommendation> list = new ArrayList<>();

//         for (Skill s : skills) {
//             Skill realSkill = skillRepo.findById(s.getId()).orElse(s);
//             list.add(computeRecommendationForStudentSkill(profile.getId(), realSkill.getId()));
//         }
//         return list;
//     }

//     @Override
//     public List<SkillGapRecommendation> getRecommendationsForStudent(
//             Long studentProfileId) {
//         return recommendationRepo.findByStudentOrdered(studentProfileId);
//     }
// }
package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final AssessmentResultRepository assessmentResultRepository;
    private final SkillGapRecommendationRepository recommendationRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final SkillRepository skillRepository;

    // Constructor injection (used in tests)
    public RecommendationServiceImpl(
            AssessmentResultRepository assessmentResultRepository,
            SkillGapRecommendationRepository recommendationRepository,
            StudentProfileRepository studentProfileRepository,
            SkillRepository skillRepository) {

        this.assessmentResultRepository = assessmentResultRepository;
        this.recommendationRepository = recommendationRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.skillRepository = skillRepository;
    }

    /**
     * Compute recommendation for ONE student + ONE skill
     * Tests: t024, t045
     */
    @Override
    public SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId) {

        StudentProfile profile = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        List<AssessmentResult> results =
                assessmentResultRepository.findByStudentProfileIdAndSkillId(studentId, skillId);

        double avgScore = results.isEmpty()
                ? 0
                : results.stream()
                         .mapToDouble(AssessmentResult::getScore)
                         .average()
                         .orElse(0);

        double gapScore = Math.max(0, 100 - avgScore);

        SkillGapRecommendation recommendation =
                SkillGapRecommendation.builder()
                        .student(profile)
                        .skill(skill)
                        .gapScore(gapScore)
                        .generatedBy("SYSTEM")
                        .build();

        return recommendationRepository.save(recommendation);
    }

    /**
     * Compute recommendations for ALL active skills for a student
     * Tests: t025, t052
     */
    @Override
    public List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId) {

        StudentProfile profile = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Skill> activeSkills = skillRepository.findByActiveTrue();

        List<SkillGapRecommendation> recommendations = new ArrayList<>();

        for (Skill s : activeSkills) {

            // IMPORTANT: tests expect findById() to be called again
            Skill realSkill = skillRepository.findById(s.getId())
                    .orElseThrow(() -> new RuntimeException("Skill not found"));

            List<AssessmentResult> results =
                    assessmentResultRepository.findByStudentProfileIdAndSkillId(
                            profile.getId(), realSkill.getId());

            double avgScore = results.isEmpty()
                    ? 0
                    : results.stream()
                             .mapToDouble(AssessmentResult::getScore)
                             .average()
                             .orElse(0);

            double gapScore = Math.max(0, 100 - avgScore);

            SkillGapRecommendation rec =
                    SkillGapRecommendation.builder()
                            .student(profile)
                            .skill(realSkill)
                            .gapScore(gapScore)
                            .generatedBy("SYSTEM")
                            .build();

            recommendations.add(recommendationRepository.save(rec));
        }

        return recommendations;
    }

    /**
     * Fetch recommendation history for a student
     * Test: t038
     */
    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {

        return recommendationRepository.findByStudentOrdered(studentId);
    }
}
