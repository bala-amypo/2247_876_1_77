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
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Skill skill = skillRepo.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        List<AssessmentResult> results =
                assessmentRepo.findByStudentProfileIdAndSkillId(studentId, skillId);

        double avgScore = results.stream()
                .mapToDouble(AssessmentResult::getScore)
                .average()
                .orElse(0.0);

        double gap = Math.max(0, skill.getMinCompetencyScore() - avgScore);

        SkillGapRecommendation rec = SkillGapRecommendation.builder()
                .studentProfile(profile)
                .skill(skill)
                .gapScore(gap)
                .priority(gap > 30 ? "HIGH" : "MEDIUM")
                .recommendedAction("Practice more")
                .generatedBy("SYSTEM")
                .generatedAt(Instant.now())
                .build();

        return recommendationRepo.save(rec);
    }

    @Override
    public List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId) {

        StudentProfile profile = profileRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Skill> skills = skillRepo.findByActiveTrue();
        List<SkillGapRecommendation> list = new ArrayList<>();

        for (Skill s : skills) {
            list.add(computeRecommendationForStudentSkill(profile.getId(), s.getId()));
        }

        return list;
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
        return recommendationRepo.findByStudentProfileIdOrderByGeneratedAtDesc(studentId);
    }
}
