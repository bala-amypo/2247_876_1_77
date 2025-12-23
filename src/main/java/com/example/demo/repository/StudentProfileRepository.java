package com.example.demo.repository;

import com.example.demo.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentProfileRepository
        extends JpaRepository<StudentProfile, Long> {

    // REQUIRED by tests
    boolean existsByEnrollmentId(String enrollmentId);

    // REQUIRED by tests
    Optional<StudentProfile> findByEnrollmentId(String enrollmentId);

    // REQUIRED by tests
    Optional<StudentProfile> findByUserId(Long userId);
}
