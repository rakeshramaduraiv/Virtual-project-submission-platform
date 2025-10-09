package com.examly.springapp.controller;

import com.examly.springapp.dto.EvaluationDto;
import com.examly.springapp.model.Evaluation;
import com.examly.springapp.model.User;
import com.examly.springapp.service.EvaluationService;
import com.examly.springapp.service.ProjectService;
import com.examly.springapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping
    @PreAuthorize("hasRole('JUDGE')")
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody EvaluationDto evaluationDto, Authentication authentication) {
        try {
            User currentUser = userService.findByEmail(authentication.getName()).orElseThrow();
            
            Evaluation evaluation = new Evaluation();
            evaluation.setJudge(currentUser);
            evaluation.setProject(projectService.findById(evaluationDto.getProjectId()));
            evaluation.setCreativityScore(evaluationDto.getCreativityScore());
            evaluation.setMethodologyScore(evaluationDto.getMethodologyScore());
            evaluation.setPresentationScore(evaluationDto.getPresentationScore());
            evaluation.setFeedback(evaluationDto.getFeedback());
            
            Evaluation createdEvaluation = evaluationService.createEvaluation(evaluation);
            return ResponseEntity.ok(createdEvaluation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Evaluation>> getEvaluations(Authentication authentication) {
        try {
            User currentUser = userService.findByEmail(authentication.getName()).orElseThrow();
            List<Evaluation> evaluations = evaluationService.getEvaluationsByJudge(currentUser);
            
            return ResponseEntity.ok(evaluations);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Evaluation>> getEvaluationsByProject(@PathVariable Long projectId) {
        try {
            List<Evaluation> evaluations = evaluationService.getEvaluationsByProject(projectService.findById(projectId));
            return ResponseEntity.ok(evaluations);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('JUDGE')")
    public ResponseEntity<Evaluation> updateEvaluation(@PathVariable Long id, @RequestBody EvaluationDto evaluationDto, Authentication authentication) {
        try {
            User currentUser = userService.findByEmail(authentication.getName()).orElseThrow();
            Evaluation existingEvaluation = evaluationService.findById(id);
            
            if (!existingEvaluation.getJudge().getId().equals(currentUser.getId())) {
                return ResponseEntity.status(403).build();
            }
            
            Evaluation evaluation = new Evaluation();
            evaluation.setCreativityScore(evaluationDto.getCreativityScore());
            evaluation.setMethodologyScore(evaluationDto.getMethodologyScore());
            evaluation.setPresentationScore(evaluationDto.getPresentationScore());
            evaluation.setFeedback(evaluationDto.getFeedback());
            
            Evaluation updatedEvaluation = evaluationService.updateEvaluation(id, evaluation);
            return ResponseEntity.ok(updatedEvaluation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}