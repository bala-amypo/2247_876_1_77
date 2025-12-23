package com.example.demo.controller;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.serviceimpl.SkillGapServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gaps")
public class SkillGapController {

    private final SkillGapServiceImpl service;

    public SkillGapController(SkillGapServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<SkillGapRecord> findAll() {
        return service.findAll();
    }
}
