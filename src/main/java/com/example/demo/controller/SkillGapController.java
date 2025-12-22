package com.example.demo.controller;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.service.SkillGapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gaps")
public class SkillGapController {

    private final SkillGapService skillGapService;

    public SkillGapController(SkillGapService skillGapService) {
        this.skillGapService = skillGapService;
    }

    @PostMapping("/compute/{studentId}")
    public ResponseEntity<String> computeGaps(@PathVariable Long studentId) {
        skillGapService.computeGaps(studentId);
        return ResponseEntity.ok("Skill gaps computed");
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SkillGapRecord>> getGaps(@PathVariable Long studentId) {
        return ResponseEntity.ok(skillGapService.getGapsByStudent(studentId));
    }
}
