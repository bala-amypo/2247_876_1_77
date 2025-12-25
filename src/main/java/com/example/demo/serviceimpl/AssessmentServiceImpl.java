package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentResultRepository repository;

    public AssessmentServiceImpl(AssessmentResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssessmentResult createAssessmentResult(AssessmentResult assessmentResult) {
        return repository.save(assessmentResult);
    }

    @Override
    public List<AssessmentResult> getAllAssessmentResults() {
        return repository.findAll();
    }
}
