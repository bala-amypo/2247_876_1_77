package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;

import java.util.List;

public class AssessmentServiceImpl {

    private final AssessmentResultRepository repository;

    // REQUIRED by test
    public AssessmentServiceImpl(AssessmentResultRepository repository) {
        this.repository = repository;
    }

    public AssessmentResult save(AssessmentResult result) {
        return repository.save(result);
    }

    public List<AssessmentResult> findRecentByStudent(Long studentId) {
        return repository.findRecentByStudent(studentId);
    }

    public AssessmentResult findByStudentAndSkill(Long studentId, Long skillId) {
        return repository
                .findByStudentProfileIdAndSkillId(studentId, skillId)
                .orElse(null);
    }
}
