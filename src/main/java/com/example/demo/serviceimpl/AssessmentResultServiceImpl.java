package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;

import java.util.List;

public class AssessmentServiceImpl {

    private final AssessmentResultRepository repository;

    // REQUIRED constructor (tests use new)
    public AssessmentServiceImpl(AssessmentResultRepository repository) {
        this.repository = repository;
    }

    // REQUIRED by controller & tests
    public AssessmentResult save(AssessmentResult result) {
        return repository.save(result);
    }

    // REQUIRED by tests
    public List<AssessmentResult> findRecentByStudent(Long studentProfileId) {
        return repository.findRecentByStudent(studentProfileId);
    }
}
