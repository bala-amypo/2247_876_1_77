package com.example.demo.service;

import com.example.demo.entity.AssessmentResult;

import java.util.List;

public interface AssessmentResultService {

    AssessmentResult createAssessmentResult(AssessmentResult assessmentResult);

    List<AssessmentResult> getResultsByStudent(Long studentProfileId);

    List<AssessmentResult> getResultsBySkill(Long skillId);
}
