package com.example.academicservice.dto.request;

import lombok.Data;

@Data
public class UniversityCreateRequest {
    private String name;
    private String description;
    private String code;
    private String address;
    private String phone;
    private String email;
}
