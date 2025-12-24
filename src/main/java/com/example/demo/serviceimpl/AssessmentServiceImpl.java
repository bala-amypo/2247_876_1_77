package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;

import java.util.List;

public class AssessmentResultServiceImpl {

    private final AssessmentResultRepository repository;

    public AssessmentResultServiceImpl(AssessmentResultRepository repository) {
        this.repository = repository;
    }

    public AssessmentResult save(AssessmentResult result) {
        return repository.save(result);
    }

    public List<AssessmentResult> findByStudent(Long studentProfileId) {
        return repository.findByStudentProfileId(studentProfileId);
    }

    public List<AssessmentResult> findBySkill(Long skillId) {
        return repository.findBySkillId(skillId);
    }
}
