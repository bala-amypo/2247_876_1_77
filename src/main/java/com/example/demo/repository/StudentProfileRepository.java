// package com.example.demo.repository;

// import com.example.demo.entity.StudentProfile;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.Optional;

// public interface StudentProfileRepository
//         extends JpaRepository<StudentProfile, Long> {

//     boolean existsByEnrollmentId(String enrollmentId);

//     Optional<StudentProfile> findByEnrollmentId(String enrollmentId);

//     Optional<StudentProfile> findByUserId(Long userId);
//}
package com.example.demo.repository;

import com.example.demo.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
@Repository
public interface StudentProfileRepository
        extends JpaRepository<StudentProfile, Long> {

    // FIX: map method name to REAL entity field
    @Query("""
        SELECT s
        FROM StudentProfile s
        WHERE s.student.id = ?1
    """)
    Optional<StudentProfile> findByUserId(Long userId);
}
