// package com.example.demo.serviceimpl;

// import com.example.demo.entity.AssessmentResult;
// import com.example.demo.repository.AssessmentResultRepository;
// import com.example.demo.service.AssessmentService;

// public class AssessmentServiceImpl implements AssessmentService {

//     private final AssessmentResultRepository repository;

//     public AssessmentServiceImpl(AssessmentResultRepository repository) {
//         this.repository = repository;
//     }

//     @Override
//     public AssessmentResult recordAssessment(AssessmentResult result) {

//         if (result.getScore() == null) {
//             throw new IllegalArgumentException("Score cannot be null");
//         }

//         if (result.getMaxScore() == null) {
//             result.setMaxScore(100.0);
//         }

//         if (result.getScore() < 0 || result.getScore() > result.getMaxScore()) {
//             throw new IllegalArgumentException("Score out of range");
//         }

//         return repository.save(result);
//     }
// }
package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentService;
import org.springframework.stereotype.Service;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentResultRepository assessmentResultRepository;

    // Constructor injection (used in tests)
    public AssessmentServiceImpl(AssessmentResultRepository assessmentResultRepository) {
        this.assessmentResultRepository = assessmentResultRepository;
    }

    /**
     * Records an assessment result
     * Validation rules STRICTLY based on TestNG tests
     */
    @Override
    public AssessmentResult recordAssessment(AssessmentResult result) {

        // ❌ Test t041: null score must fail
        if (result.getScore() == null) {
            throw new IllegalArgumentException("Score cannot be null");
        }

        // ✅ Test t017: default maxScore must be 100.0
        if (result.getMaxScore() == null) {
            result.setMaxScore(100.0);
        }

        // ❌ Test t008: score > maxScore must fail
        // ❌ Test t036: boundaries must be valid
        if (result.getScore() < 0 || result.getScore() > result.getMaxScore()) {
            throw new IllegalArgumentException("Score must be between 0 and maxScore");
        }

        // ✅ Save and return (t007)
        return assessmentResultRepository.save(result);
    }
}
