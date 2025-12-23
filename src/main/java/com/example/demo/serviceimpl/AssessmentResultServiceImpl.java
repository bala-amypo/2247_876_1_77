package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;

import java.util.List;

public class AssessmentResultServiceImpl {

    private final AssessmentResultRepository repository;

    // Constructor used by tests
    public AssessmentResultServiceImpl(AssessmentResultRepository repository) {
        this.repository = repository;
    }

    public AssessmentResult save(AssessmentResult result) {
        return repository.save(result);
    }

    public List<AssessmentResult> findRecentByStudent(Long studentProfileId) {
        return repository.findRecentByStudent(studentProfileId);
    }
}
