package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentResultService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentResultServiceImpl implements AssessmentResultService {

    private final AssessmentResultRepository assessmentResultRepository;

    public AssessmentResultServiceImpl(AssessmentResultRepository assessmentResultRepository) {
        this.assessmentResultRepository = assessmentResultRepository;
    }

    @Override
    public AssessmentResult createAssessmentResult(AssessmentResult assessmentResult) {
        return assessmentResultRepository.save(assessmentResult);
    }

    @Override
    public List<AssessmentResult> getResultsByStudent(Long studentProfileId) {
        return assessmentResultRepository.findByStudentProfileId(studentProfileId);
    }

    @Override
    public List<AssessmentResult> getResultsBySkill(Long skillId) {
        return assessmentResultRepository.findBySkillId(skillId);
    }
}
