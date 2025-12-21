package com.example.demo.controller;

import com.example.demo.entity.Skill;
import com.example.demo.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    /**
     * Create a new skill
     */
    @PostMapping
    public ResponseEntity<Skill> createSkill(@Valid @RequestBody Skill skill) {
        Skill savedSkill = skillService.createSkill(skill);
        return ResponseEntity.ok(savedSkill);
    }

    /**
     * Deactivate a skill (soft delete)
     */
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Skill> deactivateSkill(@PathVariable Long id) {
        Skill skill = skillService.deactivateSkill(id);
        return ResponseEntity.ok(skill);
    }

    /**
     * Activate a skill
     */
    @PutMapping("/{id}/activate")
    public ResponseEntity<Skill> activateSkill(@PathVariable Long id) {
        Skill skill = skillService.activateSkill(id);
        return ResponseEntity.ok(skill);
    }
}
