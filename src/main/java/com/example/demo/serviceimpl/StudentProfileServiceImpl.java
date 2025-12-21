package com.example.demo.serviceimpl;

import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;

    // REQUIRED constructor signature
    public StudentProfileServiceImpl(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public StudentProfile createProfile(StudentProfile profile) {
        studentProfileRepository.findByEnrollmentId(profile.getEnrollmentId())
            .ifPresent(p -> {
                throw new IllegalArgumentException("Enrollment already exists");
            });

        return studentProfileRepository.save(profile);
    }

    @Override
    public StudentProfile getById(Long id) {
        return studentProfileRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("StudentProfile not found"));
    }

    @Override
    public StudentProfile getByEnrollmentId(String enrollmentId) {
        return studentProfileRepository.findByEnrollmentId(enrollmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("StudentProfile not found"));
    }

    @Override
    public List<StudentProfile> getAll() {
        return studentProfileRepository.findAll();
    }
}
