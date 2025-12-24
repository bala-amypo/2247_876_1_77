package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;

import java.util.List;

public class SkillServiceImpl implements SkillService {

    private final SkillRepository repository;

    public SkillServiceImpl(SkillRepository repository) {
        this.repository = repository;
    }

    @Override
    public Skill createSkill(Skill skill) {
        if (repository.findByCode(skill.getCode()).isPresent()) {
            throw new IllegalArgumentException("Skill code must be unique");
        }
        return repository.save(skill);
    }

    @Override
    public Skill updateSkill(Long id, Skill skill) {
        Skill existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        existing.setName(skill.getName());
        existing.setCategory(skill.getCategory());
        existing.setDescription(skill.getDescription());
        existing.setMinCompetencyScore(skill.getMinCompetencyScore());

        return repository.save(existing);
    }

    @Override
    public Skill getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    @Override
    public List<Skill> getActiveSkills() {
        return repository.findByActiveTrue();
    }
}
