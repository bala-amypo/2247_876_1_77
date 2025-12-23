package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class AssessmentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentProfileId;
    private Long skillId;
    private double score;
    private String assessedAt;

    /* ---------- Constructors ---------- */

    public AssessmentResult() {
    }

    public AssessmentResult(Long id, Long studentProfileId, Long skillId,
                            double score, String assessedAt) {
        this.id = id;
        this.studentProfileId = studentProfileId;
        this.skillId = skillId;
        this.score = score;
        this.assessedAt = assessedAt;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getAssessedAt() {
        return assessedAt;
    }

    public void setAssessedAt(String assessedAt) {
        this.assessedAt = assessedAt;
    }
}
