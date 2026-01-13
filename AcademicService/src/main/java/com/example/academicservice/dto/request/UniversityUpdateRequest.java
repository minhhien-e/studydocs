package com.example.academicservice.dto.request;

import lombok.Data;

@Data
public class UniversityUpdateRequest {
    private String name;
    private String description;
    private String address;
    private String phone;
    private String email;
    private Boolean isActive;
}