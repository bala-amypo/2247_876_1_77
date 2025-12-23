package com.example.demo.serviceimpl;

import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;

public class StudentProfileServiceImpl {

    private final StudentProfileRepository repository;

    public StudentProfileServiceImpl(StudentProfileRepository repository) {
        this.repository = repository;
    }

    public StudentProfile save(StudentProfile profile) {
        return repository.save(profile);
    }

    public StudentProfile findByEnrollmentId(String enrollmentId) {
        return repository.findByEnrollmentId(enrollmentId).orElse(null);
    }

    public StudentProfile findByUserId(Long userId) {
        return repository.findByUserId(userId).orElse(null);
    }
}
