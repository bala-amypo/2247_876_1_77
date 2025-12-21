package com.example.demo.entity;

import com.example.demo.entity.StudentProfile;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "assessment_results")
public class AssessmentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ---------------- Relations ---------------- */

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_profile_id", nullable = false)
    private StudentProfile studentProfile;

    @ManyToOne(optional = false)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    /* ---------------- Assessment Data ---------------- */

    @Column(nullable = false)
    private Double score;   // 0–100

    @Column(nullable = false)
    private LocalDateTime assessedAt;

    /* ---------------- Constructors ---------------- */

    public AssessmentResult() {
        this.assessedAt = LocalDateTime.now();
    }

    /* ---------------- Getters & Setters ---------------- */

    public Long getId() {
        return id;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
        this.score = score;
    }

    public LocalDateTime getAssessedAt() {
        return assessedAt;
    }

    public void setAssessedAt(LocalDateTime assessedAt) {
        this.assessedAt = assessedAt;
    }
}
