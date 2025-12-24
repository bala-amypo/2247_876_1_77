package com.example.demo.dto;

public class RecommendationRequest {

    private Long studentProfileId;
    private Long skillId;

    public RecommendationDTO() {
    }

    public RecommendationRequest(Long studentProfileId, Long skillId) {
        this.studentProfileId = studentProfileId;
        this.skillId = skillId;
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
}
