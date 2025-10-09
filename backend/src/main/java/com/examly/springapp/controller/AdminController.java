package com.examly.springapp.controller;

import com.examly.springapp.dto.UserRegistrationDto;
import com.examly.springapp.dto.UserUpdateDto;
import com.examly.springapp.model.Role;
import com.examly.springapp.model.User;
import com.examly.springapp.service.ProjectService;
import com.examly.springapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.findAllActiveUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody UserRegistrationDto userDto) {
        try {
            User user = new User();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setRole(userDto.getRole());
            user.setSchool(userDto.getSchool());
            user.setGrade(userDto.getGrade());
            
            User createdUser = userService.createUser(user);
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto userDto) {
        try {
            User user = new User();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setRole(userDto.getRole());
            user.setSchool(userDto.getSchool());
            user.setGrade(userDto.getGrade());
            
            User updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deactivateUser(@PathVariable Long id) {
        try {
            userService.deactivateUser(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/analytics")
    public ResponseEntity<Map<String, Object>> getAnalytics() {
        try {
            Map<String, Object> analytics = new HashMap<>();
            
            List<User> allUsers = userService.findAllActiveUsers();
            analytics.put("totalUsers", allUsers.size());
            analytics.put("totalStudents", userService.findByRole(Role.STUDENT).size());
            analytics.put("totalJudges", userService.findByRole(Role.JUDGE).size());
            analytics.put("totalTeachers", userService.findByRole(Role.TEACHER).size());
            analytics.put("totalProjects", projectService.getAllProjects().size());
            
            return ResponseEntity.ok(analytics);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/reports")
    public ResponseEntity<Map<String, Object>> getReports() {
        try {
            Map<String, Object> reports = new HashMap<>();
            reports.put("projects", projectService.getAllProjects());
            reports.put("users", userService.findAllActiveUsers());
            
            return ResponseEntity.ok(reports);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/audit-logs")
    public ResponseEntity<Map<String, Object>> getAuditLogs() {
        try {
            Map<String, Object> auditLogs = new HashMap<>();
            auditLogs.put("logs", List.of());
            
            return ResponseEntity.ok(auditLogs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}