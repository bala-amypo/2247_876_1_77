package com.example.demo.serviceimpl;

import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;

public class StudentProfileServiceImpl {

    private final StudentProfileRepository repository;

    // REQUIRED by test
    public StudentProfileServiceImpl(StudentProfileRepository repository) {
        this.repository = repository;
    }

    public StudentProfile createOrUpdateProfile(StudentProfile profile) {
        return repository.save(profile);
    }

    public StudentProfile getByUserId(Long userId) {
        return repository.findByUserId(userId).orElse(null);
    }

    public StudentProfile getByEnrollmentId(String enrollmentId) {
        return repository.findByEnrollmentId(enrollmentId).orElse(null);
    }
}
