package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.repository.SkillRepository;

import java.util.List;

public class SkillServiceImpl {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }

    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    public Skill findByCode(String code) {
        return skillRepository.findByCode(code).orElse(null);
    }
}
