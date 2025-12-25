package com.example.demo.serviceimpl;

import com.example.demo.service.AssessmentService;
import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ⭐ REQUIRED
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentResultRepository repository;

    public AssessmentServiceImpl(AssessmentResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssessmentResult recordAssessment(AssessmentResult result) {
        return repository.save(result);
    }

    @Override
    public List<AssessmentResult> getAll() {
        return repository.findAll();
    }
}
