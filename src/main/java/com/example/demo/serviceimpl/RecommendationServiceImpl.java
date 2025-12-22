package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final SkillGapRecordRepository gapRepo;
    private final SkillGapRecommendationRepository recRepo;

    public RecommendationServiceImpl(
            SkillGapRecordRepository gapRepo,
            SkillGapRecommendationRepository recRepo) {
        this.gapRepo = gapRepo;
        this.recRepo = recRepo;
    }

    @Override
    public void generateRecommendations(Long studentProfileId) {

        List<SkillGapRecord> gaps =
                gapRepo.findByStudentProfileId(studentProfileId);

        for (SkillGapRecord gap : gaps) {

            SkillGapRecommendation rec = new SkillGapRecommendation();
            rec.setStudentProfile(gap.getStudentProfile());
            rec.setSkill(gap.getSkill());
            rec.setGapScore(gap.getGapScore());
            rec.setGeneratedBy("SYSTEM");

            if (gap.getGapScore() > 20) {
                rec.setPriority("HIGH");
                rec.setRecommendedAction("Immediate training required");
            } else if (gap.getGapScore() >= 10) {
                rec.setPriority("MEDIUM");
                rec.setRecommendedAction("Practice recommended");
            } else {
                rec.setPriority("LOW");
                rec.setRecommendedAction("Minor improvement needed");
            }

            recRepo.save(rec);
        }
    }

    @Override
    public List<SkillGapRecommendation>
    getRecommendationsByStudent(Long studentProfileId) {

        return recRepo
                .findByStudentProfileIdOrderByGeneratedAtDesc(studentProfileId);
    }
}
