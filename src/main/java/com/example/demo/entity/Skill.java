package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // REQUIRED BY TEST CASES
    private String code;

    private String skillName;
    private String category;
    private String description;
    private int minCompetencyScore;

    private Boolean active = true;   // ✅ Boolean + default

    /* ---------- Constructors ---------- */

    public Skill() {
    }

    public Skill(Long id, String code, String skillName, String category,
                 String description, int minCompetencyScore, Boolean active) {
        this.id = id;
        this.code = code;
        this.skillName = skillName;
        this.category = category;
        this.description = description;
        this.minCompetencyScore = minCompetencyScore;
        this.active = active;
    }

    /* ---------- Getters & Setters ---------- */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinCompetencyScore() {
        return minCompetencyScore;
    }

    public void setMinCompetencyScore(int minCompetencyScore) {
        this.minCompetencyScore = minCompetencyScore;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
