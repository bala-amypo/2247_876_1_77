package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.SkillGapService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillGapServiceImpl implements SkillGapService {

    private final SkillRepository skillRepository;
    private final AssessmentResultRepository assessmentResultRepository;
    private final SkillGapRecordRepository skillGapRecordRepository;
    private final StudentProfileRepository studentProfileRepository;

    public SkillGapServiceImpl(
            SkillRepository skillRepository,
            AssessmentResultRepository assessmentResultRepository,
            SkillGapRecordRepository skillGapRecordRepository,
            StudentProfileRepository studentProfileRepository) {

        this.skillRepository = skillRepository;
        this.assessmentResultRepository = assessmentResultRepository;
        this.skillGapRecordRepository = skillGapRecordRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public void computeGaps(Long studentProfileId) {

        StudentProfile student = studentProfileRepository.findById(studentProfileId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Skill> skills = skillRepository.findAll();

        for (Skill skill : skills) {

            List<AssessmentResult> results =
                    assessmentResultRepository.findByStudentProfileId(studentProfileId);

            if (results.isEmpty()) continue;

            AssessmentResult latest = results.get(results.size() - 1);

            double current = latest.getScore();
            double target = skill.getMinCompetencyScore();
            double gap = Math.max(0, target - current);

            SkillGapRecord record = new SkillGapRecord();
            record.setStudentProfile(student);
            record.setSkill(skill);
            record.setCurrentScore(current);
            record.setTargetScore(target);
            record.setGapScore(gap);

            skillGapRecordRepository.save(record);
        }
    }

    @Override
    public List<SkillGapRecord> getGapsByStudent(Long studentProfileId) {
        return skillGapRecordRepository.findByStudentProfileId(studentProfileId);
    }
}
