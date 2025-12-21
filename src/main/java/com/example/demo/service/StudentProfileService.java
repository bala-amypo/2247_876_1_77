package com.example.demo.service;

import com.example.demo.entity.StudentProfile;

import java.util.List;

public interface StudentProfileService {

    StudentProfile createProfile(StudentProfile profile);

    StudentProfile getById(Long id);

    StudentProfile getByEnrollmentId(String enrollmentId);

    List<StudentProfile> getAll();
}
