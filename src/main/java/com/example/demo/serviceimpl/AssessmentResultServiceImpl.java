package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ✅ REQUIRED for Spring context
public class AssessmentResultServiceImpl {

    private final AssessmentResultRepository repository;

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
