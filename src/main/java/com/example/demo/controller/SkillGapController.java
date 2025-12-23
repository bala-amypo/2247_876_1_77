package com.example.demo.controller;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.service.SkillGapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gaps")
public class SkillGapController {

    private final SkillGapService skillGapService;

    public SkillGapController(SkillGapService skillGapService) {
        this.skillGapService = skillGapService;
    }

    @PostMapping
    public ResponseEntity<SkillGapRecord> createGap(
            @RequestBody SkillGapRecord record) {
        return ResponseEntity.ok(
                skillGapService.createSkillGapRecord(record)
        );
    }

    @GetMapping
    public ResponseEntity<List<SkillGapRecord>> getAllGaps() {
        return ResponseEntity.ok(
                skillGapService.getAllSkillGapRecords()
        );
    }
}
