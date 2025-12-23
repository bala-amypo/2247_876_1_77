package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import org.springframework.stereotype.Service;

@Service
public class AssessmentServiceImpl {

    private final AssessmentResultRepository repository;

    public AssessmentServiceImpl(AssessmentResultRepository repository) {
        this.repository = repository;
    }

    public AssessmentResult save(AssessmentResult result) {
        return repository.save(result);
    }
}
