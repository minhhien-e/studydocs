package com.example.academicservice.dto.request;

import lombok.Data;
import java.util.UUID;

/**
 * DTO để tạo mới Subject
 */
@Data
public class SubjectCreateRequest {

    private UUID departmentId;     // ID của bộ môn

    private String name;           // Tên môn học

    private String description;    // Mô tả môn học

    private String code;           // Mã môn học
}
