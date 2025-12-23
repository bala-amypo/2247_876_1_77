package com.example.demo.service.impl;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.repository.SkillGapRecordRepository;
import com.example.demo.service.SkillGapService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillGapServiceImpl implements SkillGapService {

    private final SkillGapRecordRepository skillGapRecordRepository;

    public SkillGapServiceImpl(SkillGapRecordRepository skillGapRecordRepository) {
        this.skillGapRecordRepository = skillGapRecordRepository;
    }

    @Override
    public SkillGapRecord createSkillGapRecord(SkillGapRecord record) {
        return skillGapRecordRepository.save(record);
    }

    @Override
    public List<SkillGapRecord> getAllSkillGapRecords() {
        return skillGapRecordRepository.findAll();
    }
}
