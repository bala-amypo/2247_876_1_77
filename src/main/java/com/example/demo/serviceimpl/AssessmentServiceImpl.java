// package com.example.demo.serviceimpl;

// import com.example.demo.entity.AssessmentResult;
// import com.example.demo.repository.AssessmentResultRepository;

// public class AssessmentServiceImpl {

//     private final AssessmentResultRepository repo;

//     public AssessmentServiceImpl(AssessmentResultRepository repo) {
//         this.repo = repo;
//     }

//     public AssessmentResult recordAssessment(AssessmentResult r) {
//         if (r.getScore() == null)
//             throw new IllegalArgumentException("Score required");
//         if (r.getScore() < 0 || r.getScore() > r.getMaxScore())
//             throw new IllegalArgumentException("Score invalid");
//         return repo.save(r);
//     }
//}
package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentService;
import org.springframework.stereotype.Service;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentResultRepository repository;

    public AssessmentServiceImpl(AssessmentResultRepository repository) {
        this.repository = repository;
    }

    // ✅ MUST MATCH INTERFACE EXACTLY
    @Override
    public AssessmentResult recordAssessment(AssessmentResult result) {
        return repository.save(result);
    }
}
