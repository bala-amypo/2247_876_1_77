package com.example.demo.serviceimpl;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.repository.SkillGapRecordRepository;

import java.util.List;

public class SkillGapServiceImpl {

    private final SkillGapRecordRepository skillGapRecordRepository;

    // REQUIRED by test
    public SkillGapServiceImpl(SkillGapRecordRepository skillGapRecordRepository) {
        this.skillGapRecordRepository = skillGapRecordRepository;
    }

    public SkillGapRecord save(SkillGapRecord record) {
        return skillGapRecordRepository.save(record);
    }

    public List<SkillGapRecord> findAll() {
        return skillGapRecordRepository.findAll();
    }

    public List<SkillGapRecord> findByStudentProfileId(Long studentProfileId) {
        return skillGapRecordRepository.findByStudentProfileId(studentProfileId);
    }
}
