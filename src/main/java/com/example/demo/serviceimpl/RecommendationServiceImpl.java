// package com.example.demo.serviceimpl;

// import com.example.demo.entity.*;
// import com.example.demo.repository.*;
// import java.util.*;

// public class RecommendationServiceImpl {

//     private final AssessmentResultRepository assessmentRepo;
//     private final SkillGapRecommendationRepository recRepo;
//     private final StudentProfileRepository profileRepo;
//     private final SkillRepository skillRepo;

//     public RecommendationServiceImpl(
//             AssessmentResultRepository a,
//             SkillGapRecommendationRepository r,
//             StudentProfileRepository p,
//             SkillRepository s) {
//         this.assessmentRepo = a;
//         this.recRepo = r;
//         this.profileRepo = p;
//         this.skillRepo = s;
//     }

//     public SkillGapRecommendation computeRecommendationForStudentSkill(Long spId, Long skillId) {
//         StudentProfile sp = profileRepo.findById(spId).orElseThrow();
//         Skill skill = skillRepo.findById(skillId).orElseThrow();

//         List<AssessmentResult> results =
//                 assessmentRepo.findByStudentProfileIdAndSkillId(spId, skillId);

//         double avg = results.isEmpty() ? 0 :
//                 results.stream().mapToDouble(AssessmentResult::getScore).average().orElse(0);

//         SkillGapRecommendation rec = SkillGapRecommendation.builder()
//                 .student(sp)
//                 .skill(skill)
//                 .gapScore(100 - avg)
//                 .generatedBy("SYSTEM")
//                 .build();

//         return recRepo.save(rec);
//     }

//     public List<SkillGapRecommendation> computeRecommendationsForStudent(Long spId) {
//         StudentProfile sp = profileRepo.findById(spId).orElseThrow();
//         List<Skill> skills = skillRepo.findByActiveTrue();

//         List<SkillGapRecommendation> list = new ArrayList<>();
//         for (Skill s : skills) {
//             list.add(computeRecommendationForStudentSkill(sp.getId(), s.getId()));
//         }
//         return list;
//     }

//     public List<SkillGapRecommendation> getRecommendationsForStudent(Long id) {
//         return recRepo.findByStudentOrdered(id);
//     }
// }
package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.repository.SkillGapRecommendationRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.repository.StudentProfileRepository;

import java.util.ArrayList;
import java.util.List;

public class RecommendationServiceImpl {

    private final AssessmentResultRepository arRepo;
    private final SkillGapRecommendationRepository recRepo;
    private final StudentProfileRepository spRepo;
    private final SkillRepository skillRepo;

    public RecommendationServiceImpl(
            AssessmentResultRepository arRepo,
            SkillGapRecommendationRepository recRepo,
            StudentProfileRepository spRepo,
            SkillRepository skillRepo) {

        this.arRepo = arRepo;
        this.recRepo = recRepo;
        this.spRepo = spRepo;
        this.skillRepo = skillRepo;
    }

    public SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId) {

        StudentProfile sp = spRepo.findById(studentId).orElseThrow();
        Skill skill = skillRepo.findById(skillId).orElseThrow();

        SkillGapRecommendation recommendation = SkillGapRecommendation.builder()
                .studentProfile(sp)
                .skill(skill)
                .gapScore(50.0)
                .priority("MEDIUM")
                .recommendedAction("Practice")
                .generatedBy("SYSTEM")
                .build();

        return recRepo.save(recommendation);
    }

    public List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId) {

        spRepo.findById(studentId).orElseThrow();

        List<Skill> skills = skillRepo.findByActiveTrue();
        List<SkillGapRecommendation> result = new ArrayList<>();

        for (Skill skill : skills) {
            result.add(computeRecommendationForStudentSkill(studentId, skill.getId()));
        }

        return result;
    }

    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
        return recRepo.findByStudentOrdered(studentId);
    }
}
