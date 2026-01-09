package com.example.academicservice.dto.request;

import lombok.Data;
import java.util.UUID;

/**
 * DTO để tạo mới Faculty
 */
@Data
public class FacultyCreateRequest {
    private UUID universityId;        // ID của trường đại học
    private String name;              // Tên khoa
    private String description;      // Mô tả khoa
    private String code;              // Mã khoa
}

