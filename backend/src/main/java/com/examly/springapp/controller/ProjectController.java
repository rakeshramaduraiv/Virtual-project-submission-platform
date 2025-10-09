package com.examly.springapp.controller;

import com.examly.springapp.dto.ProjectDto;
import com.examly.springapp.model.Project;
import com.examly.springapp.model.Role;
import com.examly.springapp.model.User;
import com.examly.springapp.service.CategoryService;
import com.examly.springapp.service.ProjectService;
import com.examly.springapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects(Authentication authentication) {
        try {
            User currentUser = userService.findByEmail(authentication.getName()).orElseThrow();
            List<Project> projects;
            
            if (currentUser.getRole() == Role.STUDENT) {
                projects = projectService.getProjectsByStudent(currentUser);
            } else {
                projects = projectService.getAllProjects();
            }
            
            return ResponseEntity.ok(projects);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Project> createProject(@RequestBody ProjectDto projectDto, Authentication authentication) {
        try {
            User currentUser = userService.findByEmail(authentication.getName()).orElseThrow();
            
            Project project = new Project();
            project.setTitle(projectDto.getTitle());
            project.setAbstractText(projectDto.getAbstractText());
            project.setMethodology(projectDto.getMethodology());
            project.setStudent(currentUser);
            
            if (projectDto.getCategoryId() != null) {
                project.setCategory(categoryService.findById(projectDto.getCategoryId()));
            }
            
            Project createdProject = projectService.createProject(project);
            return ResponseEntity.ok(createdProject);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Long id) {
        try {
            Project project = projectService.findById(id);
            return ResponseEntity.ok(project);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}