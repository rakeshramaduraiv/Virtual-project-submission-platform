package com.examly.springapp.service;

import com.examly.springapp.exception.DuplicateSubmissionException;
import com.examly.springapp.exception.InvalidProjectTitleException;
import com.examly.springapp.model.Project;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project) {
        if (project.getTitle().length() < 10 || project.getTitle().length() > 200) {
            throw new InvalidProjectTitleException("Title must be between 10 and 200 characters");
        }
        
        if (projectRepository.existsByTitleAndStudentAndIsActiveTrue(project.getTitle(), project.getStudent())) {
            throw new DuplicateSubmissionException("Project with this title already exists for this student");
        }
        
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findByIsActiveTrue();
    }

    public List<Project> getProjectsByStudent(User student) {
        return projectRepository.findByStudentAndIsActiveTrue(student);
    }

    public List<Project> getProjectsByCategory(Long categoryId) {
        return projectRepository.findByCategoryIdAndIsActiveTrue(categoryId);
    }

    public Project updateProject(Long id, Project projectDetails) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        
        project.setTitle(projectDetails.getTitle());
        project.setAbstractText(projectDetails.getAbstractText());
        project.setMethodology(projectDetails.getMethodology());
        project.setCategory(projectDetails.getCategory());
        project.setStatus(projectDetails.getStatus());
        
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        project.setIsActive(false);
        projectRepository.save(project);
    }

    public Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }
}