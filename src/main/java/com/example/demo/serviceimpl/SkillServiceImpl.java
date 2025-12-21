package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;
import org.springframework.stereotype.Service;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill createSkill(Skill skill) {

        if (skillRepository.existsBySkillName(skill.getSkillName())) {
            throw new IllegalArgumentException(
                "Skill with name '" + skill.getSkillName() + "' already exists"
            );
        }

        return skillRepository.save(skill);
    }

    @Override
    public Skill deactivateSkill(Long skillId) {
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new IllegalArgumentException("Skill not found"));

        skill.setActive(false);
        return skillRepository.save(skill);
    }

    @Override
    public Skill activateSkill(Long skillId) {
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new IllegalArgumentException("Skill not found"));

        skill.setActive(true);
        return skillRepository.save(skill);
    }
}
