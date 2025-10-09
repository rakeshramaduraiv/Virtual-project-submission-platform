package com.examly.springapp.service;

import com.examly.springapp.model.Evaluation;
import com.examly.springapp.model.Project;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private NotificationService notificationService;

    public Evaluation createEvaluation(Evaluation evaluation) {
        if (evaluationRepository.existsByProjectAndJudgeAndIsActiveTrue(evaluation.getProject(), evaluation.getJudge())) {
            throw new RuntimeException("Evaluation already exists for this project by this judge");
        }
        
        Evaluation savedEvaluation = evaluationRepository.save(evaluation);
        
        // Update project status
        Project project = evaluation.getProject();
        project.setStatus(Project.ProjectStatus.EVALUATED);
        
        // Send notification to student
        notificationService.createNotification(
            "Project Evaluated",
            "Your project '" + project.getTitle() + "' has been evaluated.",
            project.getStudent()
        );
        
        return savedEvaluation;
    }

    public List<Evaluation> getEvaluationsByJudge(User judge) {
        return evaluationRepository.findByJudgeAndIsActiveTrue(judge);
    }

    public List<Evaluation> getEvaluationsByProject(Project project) {
        return evaluationRepository.findByProjectAndIsActiveTrue(project);
    }

    public Evaluation updateEvaluation(Long id, Evaluation evaluationDetails) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));
        
        evaluation.setCreativityScore(evaluationDetails.getCreativityScore());
        evaluation.setMethodologyScore(evaluationDetails.getMethodologyScore());
        evaluation.setPresentationScore(evaluationDetails.getPresentationScore());
        evaluation.setFeedback(evaluationDetails.getFeedback());
        evaluation.setRecommendation(evaluationDetails.getRecommendation());
        
        return evaluationRepository.save(evaluation);
    }

    public Evaluation findById(Long id) {
        return evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));
    }
}