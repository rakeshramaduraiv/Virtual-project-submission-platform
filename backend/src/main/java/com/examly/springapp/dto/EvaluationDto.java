package com.examly.springapp.dto;

public class EvaluationDto {
    private Long projectId;
    private Integer creativityScore;
    private Integer methodologyScore;
    private Integer presentationScore;
    private String feedback;

    public EvaluationDto() {}

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getCreativityScore() {
        return creativityScore;
    }

    public void setCreativityScore(Integer creativityScore) {
        this.creativityScore = creativityScore;
    }

    public Integer getMethodologyScore() {
        return methodologyScore;
    }

    public void setMethodologyScore(Integer methodologyScore) {
        this.methodologyScore = methodologyScore;
    }

    public Integer getPresentationScore() {
        return presentationScore;
    }

    public void setPresentationScore(Integer presentationScore) {
        this.presentationScore = presentationScore;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}