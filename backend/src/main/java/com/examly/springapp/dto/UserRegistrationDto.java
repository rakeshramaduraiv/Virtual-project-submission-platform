package com.examly.springapp.dto;

import com.examly.springapp.model.Role;

public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private String school;
    private String grade;

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getSchool() { return school; }
    public void setSchool(String school) { this.school = school; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}