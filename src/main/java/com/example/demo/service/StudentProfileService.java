package com.example.demo.service;

import com.example.demo.entity.StudentProfile;
import java.util.List;

public interface StudentProfileService {

    StudentProfile createProfile(StudentProfile profile);

    List<StudentProfile> getAll();

    // 👈 REQUIRED by test / helper doc
    StudentProfile getByEnrollmentId(String enrollmentId);
}
