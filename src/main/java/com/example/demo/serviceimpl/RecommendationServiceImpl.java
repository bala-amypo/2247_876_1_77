package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.util.*;

public class RecommendationServiceImpl {

    private final AssessmentResultRepository assessmentRepo;
    private final SkillGapRecommendationRepository recRepo;
    private final StudentProfileRepository profileRepo;
    private final SkillRepository skillRepo;

    public RecommendationServiceImpl(
            AssessmentResultRepository a,
            SkillGapRecommendationRepository r,
            StudentProfileRepository p,
            SkillRepository s) {
        this.assessmentRepo = a;
        this.recRepo = r;
        this.profileRepo = p;
        this.skillRepo = s;
    }

    public SkillGapRecommendation computeRecommendationForStudentSkill(Long spId, Long skillId) {
        StudentProfile sp = profileRepo.findById(spId).orElseThrow();
        Skill skill = skillRepo.findById(skillId).orElseThrow();

        List<AssessmentResult> results =
                assessmentRepo.findByStudentProfileIdAndSkillId(spId, skillId);

        double avg = results.isEmpty() ? 0 :
                results.stream().mapToDouble(AssessmentResult::getScore).average().orElse(0);

        SkillGapRecommendation rec = SkillGapRecommendation.builder()
                .student(sp)
                .skill(skill)
                .gapScore(100 - avg)
                .generatedBy("SYSTEM")
                .build();

        return recRepo.save(rec);
    }

    public List<SkillGapRecommendation> computeRecommendationsForStudent(Long spId) {
        StudentProfile sp = profileRepo.findById(spId).orElseThrow();
        List<Skill> skills = skillRepo.findByActiveTrue();

        List<SkillGapRecommendation> list = new ArrayList<>();
        for (Skill s : skills) {
            list.add(computeRecommendationForStudentSkill(sp.getId(), s.getId()));
        }
        return list;
    }

    public List<SkillGapRecommendation> getRecommendationsForStudent(Long id) {
        return recRepo.findByStudentOrdered(id);
    }
}
