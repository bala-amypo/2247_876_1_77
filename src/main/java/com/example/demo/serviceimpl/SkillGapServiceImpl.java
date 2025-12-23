package com.example.demo.serviceimpl;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.repository.SkillGapRecordRepository;

import java.util.List;

public class SkillGapServiceImpl {

    private final SkillGapRecordRepository repository;

    public SkillGapServiceImpl(SkillGapRecordRepository repository) {
        this.repository = repository;
    }

    public SkillGapRecord save(SkillGapRecord record) {
        return repository.save(record);
    }

    public List<SkillGapRecord> findAll() {
        return repository.findAll();
    }

    public List<SkillGapRecord> findByStudentProfileId(Long studentProfileId) {
        return repository.findByStudentProfileId(studentProfileId);
    }
}
