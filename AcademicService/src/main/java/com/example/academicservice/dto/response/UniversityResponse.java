package com.example.academicservice.dto.response;

import lombok.Data;

@Data
public class UniversityResponse {
    private Long id;
    private String name;
    private String slug;
    private String code;
    private String address;
    private Boolean isActive;
}
