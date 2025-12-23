package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String enrollmentId;
    private String cohort;
    private int yearLevel;

    private Boolean active = true;   // ✅ Boolean + default

    /* ---------- Constructors ---------- */

    public StudentProfile() {
    }

    public StudentProfile(Long id, String enrollmentId, String cohort,
                          int yearLevel, Boolean active) {
        this.id = id;
        this.enrollmentId = enrollmentId;
        this.cohort = cohort;
        this.yearLevel = yearLevel;
        this.active = active;
    }

    /* ---------- Getters & Setters ---------- */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    public int getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
