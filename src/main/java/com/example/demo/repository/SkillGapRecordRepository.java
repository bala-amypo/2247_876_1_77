package com.example.demo.repository;

import com.example.demo.entity.SkillGapRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillGapRecordRepository
        extends JpaRepository<SkillGapRecord, Long> {
}
