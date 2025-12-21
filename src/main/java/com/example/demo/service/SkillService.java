package com.example.demo.service;

import com.example.demo.entity.Skill;

import java.util.List;

public interface SkillService {

    Skill createSkill(Skill skill);

    Skill getSkillById(Long id);

    List<Skill> getAllSkills();

    Skill activateSkill(Long id);

    Skill deactivateSkill(Long id);
}
