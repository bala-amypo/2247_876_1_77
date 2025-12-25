package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
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
    public SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId) {

        StudentProfile profile = profileRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        Skill skill = skillRepo.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        List<AssessmentResult> results =
                assessmentRepo.findByStudentProfileIdAndSkillId(studentId, skillId);

        double gap = results.isEmpty()
                ? 100
                : 100 - results.get(results.size() - 1).getScore();

        SkillGapRecommendation rec = new SkillGapRecommendation();
        rec.setStudentProfile(profile);
        rec.setSkill(skill);
        rec.setGapScore(gap);
        rec.setGeneratedBy("SYSTEM");
        rec.setGeneratedAt(Instant.now());

        return recommendationRepo.save(rec);
    }

    @Override
    public List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId) {

        StudentProfile profile = profileRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        List<Skill> skills = skillRepo.findByActiveTrue();
        List<SkillGapRecommendation> result = new ArrayList<>();

        for (Skill skill : skills) {
            result.add(computeRecommendationForStudentSkill(profile.getId(), skill.getId()));
        }

        return result;
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
        return recommendationRepo
                .findByStudentProfileIdOrderByGeneratedAtDesc(studentId);
    }
}
