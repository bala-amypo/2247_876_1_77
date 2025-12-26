package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.repository.SkillRepository;
import java.util.*;

public class SkillServiceImpl {

    private final SkillRepository repo;

    public SkillServiceImpl(SkillRepository repo) {
        this.repo = repo;
    }

    public Skill createSkill(Skill s) {
        if (repo.findByCode(s.getCode()).isPresent())
            throw new IllegalArgumentException("Skill code must be unique");
        return repo.save(s);
    }

    public Skill updateSkill(Long id, Skill s) {
        Skill existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
        existing.setName(s.getName());
        return repo.save(existing);
    }

    public Skill getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    public List<Skill> getActiveSkills() {
        return repo.findByActiveTrue();
    }
}
