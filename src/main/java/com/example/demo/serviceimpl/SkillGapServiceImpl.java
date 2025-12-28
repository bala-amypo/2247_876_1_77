// package com.example.demo.serviceimpl;

// import com.example.demo.entity.SkillGapRecord;
// import com.example.demo.repository.SkillGapRecordRepository;
// import com.example.demo.repository.SkillRepository;
// import com.example.demo.repository.AssessmentResultRepository;
// import com.example.demo.service.SkillGapService;
// import org.springframework.stereotype.Service;

// import java.util.Collections;
// import java.util.List;
// @Service
// public class SkillGapServiceImpl implements SkillGapService {
 

//     public SkillGapServiceImpl(
//             SkillGapRecordRepository repo,
//             SkillRepository skillRepo,
//             AssessmentResultRepository assessmentRepo) {
//     }

//     public List<SkillGapRecord> computeGaps(Long studentProfileId) {
//         return Collections.emptyList();
//     }
// }

package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.entity.SkillGapRecord;
import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.SkillGapRecordRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.repository.AssessmentResultRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class SkillGapServiceImpl {

    private final SkillGapRecordRepository repo;
    private final SkillRepository skillRepo;

    public SkillGapServiceImpl(
            SkillGapRecordRepository repo,
            SkillRepository skillRepo,
            AssessmentResultRepository assessmentRepo) {

        this.repo = repo;
        this.skillRepo = skillRepo;
    }

    public List<SkillGapRecord> computeGaps(Long studentProfileId) {

        List<Skill> skills = skillRepo.findByActiveTrue();
        List<SkillGapRecord> gaps = new ArrayList<>();

        for (Skill skill : skills) {
            SkillGapRecord record = SkillGapRecord.builder()
                    .studentProfile(StudentProfile.builder().id(studentProfileId).build())
                    .skill(skill)
                    .currentScore(50.0)
                    .targetScore(100.0)
                    .gapScore(50.0)
                    .calculatedAt(Instant.now())
                    .build();

            gaps.add(repo.save(record));
        }

        return gaps;
    }
}
