package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationService;

import java.util.ArrayList;
import java.util.List;

public class RecommendationServiceImpl implements RecommendationService {

    private final AssessmentResultRepository assessmentRepo;
    private final SkillGapRecommendationRepository recommendationRepo;
    private final StudentProfileRepository profileRepo;
    private final SkillRepository skillRepo;

    public RecommendationServiceImpl(
            AssessmentResultRepository assessmentRepo,
            SkillGapRecommendationRepository recommendationRepo,
            StudentProfileRepository profileRepo,
            SkillRepository skillRepo) {

        this.assessmentRepo = assessmentRepo;
        this.recommendationRepo = recommendationRepo;
        this.profileRepo = profileRepo;
        this.skillRepo = skillRepo;
    }

    @Override
    public SkillGapRecommendation computeRecommendationForStudentSkill(
            Long studentProfileId, Long skillId) {

        StudentProfile profile = profileRepo.findById(studentProfileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        Skill skill = skillRepo.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        List<AssessmentResult> results =
                assessmentRepo.findByStudentProfileIdAndSkillId(studentProfileId, skillId);

        double avgScore = results.stream()
                .mapToDouble(r -> r.getScore())
                .average()
                .orElse(0.0);

        double gap = 100.0 - avgScore;

        SkillGapRecommendation rec = SkillGapRecommendation.builder()
                .studentProfile(profile)
                .skill(skill)
                .gapScore(gap)
                .build();

        return recommendationRepo.save(rec);
    }

    @Override
    public List<SkillGapRecommendation> computeRecommendationsForStudent(
            Long studentProfileId) {

        StudentProfile profile = profileRepo.findById(studentProfileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        List<Skill> skills = skillRepo.findByActiveTrue();
        List<SkillGapRecommendation> list = new ArrayList<>();

        for (Skill s : skills) {
            Skill realSkill = skillRepo.findById(s.getId()).orElse(s);
            list.add(computeRecommendationForStudentSkill(profile.getId(), realSkill.getId()));
        }
        return list;
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(
            Long studentProfileId) {
        return recommendationRepo.findByStudentOrdered(studentProfileId);
    }
}
