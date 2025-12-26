
// package com.example.demo.serviceimpl;

// import com.example.demo.entity.AssessmentResult;
// import com.example.demo.repository.AssessmentResultRepository;

// public class AssessmentServiceImpl {

//     private final AssessmentResultRepository repo;

//     public AssessmentServiceImpl(AssessmentResultRepository repo) {
//         this.repo = repo;
//     }

//     public AssessmentResult recordAssessment(AssessmentResult r) {
//         if (r.getScore() == null ||
//             r.getScore() < 0 ||
//             r.getScore() > r.getMaxScore()) {

//             throw new IllegalArgumentException("Score must be between 0 and 100");
//         }

//         return repo.save(r);
//     }
// }
package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentResultService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AssessmentResultServiceImpl implements AssessmentResultService {

    private final AssessmentResultRepository repository;

    public AssessmentResultServiceImpl(AssessmentResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssessmentResult save(AssessmentResult result) {
        return repository.save(result);
    }

    @Override
    public List<AssessmentResult> findRecentByStudent(Long studentId) {
        return repository.findRecentByStudent(studentId);
    }

    @Override
    public List<AssessmentResult> findResultsForStudentBetween(
            Long studentId,
            Instant from,
            Instant to) {
        return repository.findResultsForStudentBetween(studentId, from, to);
    }
}
