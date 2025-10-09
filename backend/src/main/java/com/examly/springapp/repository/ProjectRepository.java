package com.examly.springapp.repository;

import com.examly.springapp.model.Project;
import com.examly.springapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByStudentAndIsActiveTrue(User student);
    List<Project> findByIsActiveTrue();
    List<Project> findByCategoryIdAndIsActiveTrue(Long categoryId);
    boolean existsByTitleAndStudentAndIsActiveTrue(String title, User student);
}