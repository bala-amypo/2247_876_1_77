package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill createSkill(Skill skill) {

        if (skillRepository.findBySkillName(skill.getSkillName()).isPresent()) {
            throw new IllegalArgumentException("Skill already exists");
        }

        skill.setActive(true);
        return skillRepository.save(skill);
    }

    @Override
    public Skill getSkillById(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Skill not found"));
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill activateSkill(Long id) {
        Skill skill = getSkillById(id);
        skill.setActive(true);
        return skillRepository.save(skill);
    }

    @Override
    public Skill deactivateSkill(Long id) {
        Skill skill = getSkillById(id);
        skill.setActive(false);
        return skillRepository.save(skill);
    }
}
