package com.example.academicservice.dto.request;

import lombok.Data;

/**
 * DTO để tạo mới Faculty
 */
@Data
public class FacultyCreateRequest {
    private Long universityId;        // ID của trường đại học
    private String name;              // Tên khoa
    private String description;      // Mô tả khoa
    private String code;              // Mã khoa
}

