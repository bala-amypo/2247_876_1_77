package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "student_profiles",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "enrollmentId")
    }
)
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ---------- Relationships ---------- */

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /* ---------- Fields ---------- */

    @Column(nullable = false, unique = true)
    private String enrollmentId;

    private String cohort;

    private Integer yearLevel;

    @Column(nullable = false)
    private Boolean active = true;

    /* ---------- Constructors ---------- */

    public StudentProfile() {}

    /* ---------- Getters & Setters ---------- */

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Integer getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(Integer yearLevel) {
        this.yearLevel = yearLevel;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
