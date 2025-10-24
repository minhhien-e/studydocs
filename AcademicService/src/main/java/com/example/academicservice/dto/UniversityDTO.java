package com.example.academicservice.dto;

import lombok.Data;

@Data
public class UniversityDTO {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private String code;
    private String address;
    private String phone;
    private String email;
    private Boolean isActive;
}
