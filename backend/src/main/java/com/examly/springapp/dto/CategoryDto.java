package com.examly.springapp.dto;

public class CategoryDto {
    private String name;
    private String description;

    public CategoryDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}