package com.examly.springapp.controller;

import com.examly.springapp.dto.UserUpdateDto;
import com.examly.springapp.model.User;
import com.examly.springapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object>> getProfile(Authentication authentication) {
        try {
            User user = userService.findByEmail(authentication.getName()).orElseThrow();
            
            Map<String, Object> profile = new HashMap<>();
            profile.put("id", user.getId());
            profile.put("firstName", user.getFirstName());
            profile.put("lastName", user.getLastName());
            profile.put("email", user.getEmail());
            profile.put("role", user.getRole());
            profile.put("school", user.getSchool());
            profile.put("grade", user.getGrade());
            
            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "User not found");
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<Map<String, Object>> updateProfile(@RequestBody UserUpdateDto userDto, Authentication authentication) {
        try {
            User currentUser = userService.findByEmail(authentication.getName()).orElseThrow();
            
            User userDetails = new User();
            userDetails.setFirstName(userDto.getFirstName());
            userDetails.setLastName(userDto.getLastName());
            userDetails.setEmail(userDto.getEmail());
            userDetails.setRole(userDto.getRole());
            userDetails.setSchool(userDto.getSchool());
            userDetails.setGrade(userDto.getGrade());
            
            User updatedUser = userService.updateUser(currentUser.getId(), userDetails);
            
            Map<String, Object> profile = new HashMap<>();
            profile.put("id", updatedUser.getId());
            profile.put("firstName", updatedUser.getFirstName());
            profile.put("lastName", updatedUser.getLastName());
            profile.put("email", updatedUser.getEmail());
            profile.put("role", updatedUser.getRole());
            profile.put("school", updatedUser.getSchool());
            profile.put("grade", updatedUser.getGrade());
            
            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}