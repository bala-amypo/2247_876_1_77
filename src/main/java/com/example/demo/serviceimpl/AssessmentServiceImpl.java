package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;

public class AssessmentServiceImpl {

    private final AssessmentResultRepository repo;

    public AssessmentServiceImpl(AssessmentResultRepository repo) {
        this.repo = repo;
    }

    public AssessmentResult recordAssessment(AssessmentResult r) {
        if (r.getScore() == null)
            throw new IllegalArgumentException("Score required");
        if (r.getScore() < 0 || r.getScore() > r.getMaxScore())
            throw new IllegalArgumentException("Score invalid");
        return repo.save(r);
    }
}

