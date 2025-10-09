package com.examly.springapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

@Entity
@Table(name = "evaluations")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1) @Max(10)
    private Integer creativityScore;

    @Min(1) @Max(10)
    private Integer methodologyScore;

    @Min(1) @Max(10)
    private Integer presentationScore;

    @Column(columnDefinition = "TEXT")
    private String feedback;

    @Enumerated(EnumType.STRING)
    private Recommendation recommendation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "judge_id")
    private User judge;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum Recommendation {
        EXCELLENT, GOOD, SATISFACTORY, NEEDS_IMPROVEMENT
    }

    // Constructors
    public Evaluation() {}

    public Evaluation(Integer creativityScore, Integer methodologyScore, Integer presentationScore, 
                     String feedback, Recommendation recommendation, Project project, User judge) {
        this.creativityScore = creativityScore;
        this.methodologyScore = methodologyScore;
        this.presentationScore = presentationScore;
        this.feedback = feedback;
        this.recommendation = recommendation;
        this.project = project;
        this.judge = judge;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getCreativityScore() { return creativityScore; }
    public void setCreativityScore(Integer creativityScore) { this.creativityScore = creativityScore; }

    public Integer getMethodologyScore() { return methodologyScore; }
    public void setMethodologyScore(Integer methodologyScore) { this.methodologyScore = methodologyScore; }

    public Integer getPresentationScore() { return presentationScore; }
    public void setPresentationScore(Integer presentationScore) { this.presentationScore = presentationScore; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public Recommendation getRecommendation() { return recommendation; }
    public void setRecommendation(Recommendation recommendation) { this.recommendation = recommendation; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }

    public User getJudge() { return judge; }
    public void setJudge(User judge) { this.judge = judge; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public Double getTotalScore() {
        if (creativityScore != null && methodologyScore != null && presentationScore != null) {
            return (creativityScore + methodologyScore + presentationScore) / 3.0;
        }
        return null;
    }
}