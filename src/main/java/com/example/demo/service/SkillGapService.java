package com.example.demo.service;

import com.example.demo.entity.SkillGapRecord;
import java.util.List;

public interface SkillGapService {

    SkillGapRecord createSkillGapRecord(SkillGapRecord record);

    List<SkillGapRecord> getAllSkillGapRecords();
}
