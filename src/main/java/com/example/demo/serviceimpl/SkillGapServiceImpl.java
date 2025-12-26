package com.example.demo.serviceimpl;

import com.example.demo.repository.*;
import com.example.demo.entity.*;
import java.util.*;

public class SkillGapServiceImpl {

    public SkillGapServiceImpl(
            SkillGapRecordRepository repo,
            SkillRepository skillRepo,
            AssessmentResultRepository arRepo) {
    }

    public List<SkillGapRecord> computeGaps(Long studentProfileId) {
        return Collections.emptyList();
    }
}
