package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class SkillGapRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentProfileId;
    private Long skillId;

    private String recommendedAction;
    private String priority;
    private double gapScore;
    private String generatedBy;
    private String generatedAt;

    /* ---------- Constructors ---------- */

    public SkillGapRecommendation() {
    }

    public SkillGapRecommendation(Long id, Long studentProfileId, Long skillId,
                                  String recommendedAction, String priority,
                                  double gapScore, String generatedBy,
                                  String generatedAt) {
        this.id = id;
        this.studentProfileId = studentProfileId;
        this.skillId = skillId;
        this.recommendedAction = recommendedAction;
        this.priority = priority;
        this.gapScore = gapScore;
        this.generatedBy = generatedBy;
        this.generatedAt = generatedAt;
    }

    /* ---------- Getters & Setters ---------- */

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

    public String getRecommendedAction() {
        return recommendedAction;
    }

    public void setRecommendedAction(String recommendedAction) {
        this.recommendedAction = recommendedAction;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public double getGapScore() {
        return gapScore;
    }

    public void setGapScore(double gapScore) {
        this.gapScore = gapScore;
    }

    public String getGeneratedBy() {
        return generatedBy;
    }

    public void setGeneratedBy(String generatedBy) {
        this.generatedBy = generatedBy;
    }

    public String getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(String generatedAt) {
        this.generatedAt = generatedAt;
    }
}
