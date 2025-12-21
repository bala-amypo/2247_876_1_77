package com.example.demo.service;

import com.example.demo.entity.Skill;

public interface SkillService {

    Skill createSkill(Skill skill);

    Skill deactivateSkill(Long skillId);

    Skill activateSkill(Long skillId);
}
