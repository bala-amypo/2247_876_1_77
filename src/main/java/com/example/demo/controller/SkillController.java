package com.example.demo.controller;

import com.example.demo.entity.Skill;
import com.example.demo.serviceimpl.SkillServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private final SkillServiceImpl service;

    public SkillController(SkillServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public Skill save(@RequestBody Skill skill) {
        return service.save(skill);
    }

    @GetMapping
    public List<Skill> findAll() {
        return service.findAll();
    }
}
