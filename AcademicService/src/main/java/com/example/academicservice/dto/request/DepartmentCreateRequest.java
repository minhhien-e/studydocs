package com.example.academicservice.dto.request;

import lombok.Data;
import java.util.UUID;

/**
 * DTO để tạo mới Department
 */
@Data
public class DepartmentCreateRequest {

    private UUID facultyId;        // ID của khoa

    private String name;           // Tên bộ môn

    private String description;    // Mô tả bộ môn

    private String code;           // Mã bộ môn
}

