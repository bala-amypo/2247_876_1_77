package com.example.demo.dto;

public class RecommendationDTO {

    private Long studentId;
    private String recommendation;

    public RecommendationDTO() {}

    public RecommendationDTO(Long studentId, String recommendation) {
        this.studentId = studentId;
        this.recommendation = recommendation;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}
