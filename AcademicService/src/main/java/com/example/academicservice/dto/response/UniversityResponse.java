package com.example.academicservice.dto.response;

import lombok.Data;
import java.util.UUID;

@Data
public class UniversityResponse {
    private UUID id;
    private String name;
    private String slug;
    private String code;
    private String address;
    private Boolean isActive;
}
