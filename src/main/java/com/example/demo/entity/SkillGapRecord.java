package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SkillGapRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentProfileId;
    private Long skillId;

    private double currentScore;
    private double targetScore;
    private double gapScore;
    private String calculatedAt;

    // -------- Constructors --------

    public SkillGapRecord() {
    }

    public SkillGapRecord(Long id,
                          Long studentProfileId,
                          Long skillId,
                          double currentScore,
                          double targetScore,
                          double gapScore,
                          String calculatedAt) {
        this.id = id;
        this.studentProfileId = studentProfileId;
        this.skillId = skillId;
        this.currentScore = currentScore;
        this.targetScore = targetScore;
        this.gapScore = gapScore;
        this.calculatedAt = calculatedAt;
    }

    // -------- Getters & Setters --------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentProfileId() {
        return studentProfileId;
    }

    public void setStudentProfileId(Long studentProfileId) {
        this.studentProfileId = studentProfileId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public double getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(double currentScore) {
        this.currentScore = currentScore;
    }

    public double getTargetScore() {
        return targetScore;
    }

    public void setTargetScore(double targetScore) {
        this.targetScore = targetScore;
    }

    public double getGapScore() {
        return gapScore;
    }

    public void setGapScore(double gapScore) {
        this.gapScore = gapScore;
    }

    public String getCalculatedAt() {
        return calculatedAt;
    }

    public void setCalculatedAt(String calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}
+