package com.examly.springapp.repository;

import com.examly.springapp.model.Evaluation;
import com.examly.springapp.model.Project;
import com.examly.springapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findByJudgeAndIsActiveTrue(User judge);
    List<Evaluation> findByProjectAndIsActiveTrue(Project project);
    Optional<Evaluation> findByProjectAndJudgeAndIsActiveTrue(Project project, User judge);
    boolean existsByProjectAndJudgeAndIsActiveTrue(Project project, User judge);
}