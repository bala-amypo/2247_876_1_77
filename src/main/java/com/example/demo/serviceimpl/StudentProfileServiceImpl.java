package com.example.demo.serviceimpl;

import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository repository;

    public StudentProfileServiceImpl(StudentProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public StudentProfile createProfile(StudentProfile profile) {

        if (repository.findByEnrollmentId(profile.getEnrollmentId()).isPresent()) {
            throw new IllegalArgumentException("Enrollment already exists");
        }

        return repository.save(profile);
    }

    @Override
    public List<StudentProfile> getAll() {
        return repository.findAll();
    }

    // ✅ THIS METHOD WAS MISSING — NOW FIXED
    @Override
    public StudentProfile getByEnrollmentId(String enrollmentId) {
        return repository.findByEnrollmentId(enrollmentId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Student not found"));
    }
}
