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

    private final AssessmentResultRepository assessmentRepository;
    private final SkillGapRecommendationRepository recommendationRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final SkillRepository skillRepository;

    public RecommendationServiceImpl(
            AssessmentResultRepository assessmentRepository,
            SkillGapRecommendationRepository recommendationRepository,
            StudentProfileRepository studentProfileRepository,
            SkillRepository skillRepository) {

        this.assessmentRepository = assessmentRepository;
        this.recommendationRepository = recommendationRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId) {

        StudentProfile profile = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        List<AssessmentResult> results =
                assessmentRepository.findByStudentProfileIdAndSkillId(studentId, skillId);

        double avgScore = results.stream()
                .mapToDouble(AssessmentResult::getScore)
                .average()
                .orElse(0);

        double gap = Math.max(0, 100 - avgScore);

        SkillGapRecommendation rec = SkillGapRecommendation.builder()
                .studentProfile(profile)
                .skill(skill)
                .gapScore(gap)
                .generatedBy("SYSTEM")
                .generatedAt(Instant.now())
                .build();

        return recommendationRepository.save(rec);
    }

    @Override
    public List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId) {

        StudentProfile profile = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        List<Skill> skills = skillRepository.findByActiveTrue();
        List<SkillGapRecommendation> list = new ArrayList<>();

        for (Skill skill : skills) {
            Skill realSkill = skillRepository.findById(skill.getId()).orElse(skill);
            list.add(computeRecommendationForStudentSkill(profile.getId(), realSkill.getId()));
        }
        return list;
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
        return recommendationRepository.findByStudentOrdered(studentId);
    }
}
