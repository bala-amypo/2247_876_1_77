
// package com.example.demo.service;

// import com.example.demo.entity.AssessmentResult;

// public interface AssessmentService {
//     AssessmentResult recordAssessment(AssessmentResult result);
// }
package com.example.demo.service;

import com.example.demo.entity.AssessmentResult;

import java.time.Instant;
import java.util.List;

public interface AssessmentResultService {

    AssessmentResult save(AssessmentResult result);

    List<AssessmentResult> findRecentByStudent(Long studentId);

    List<AssessmentResult> findResultsForStudentBetween(
            Long studentId,
            Instant from,
            Instant to
    );
}
