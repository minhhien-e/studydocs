package com.example.academicservice.dto.request;

import lombok.Data;

/**
 * DTO để cập nhật thông tin Department
 */
@Data
public class DepartmentUpdateRequest {

    private String name;           // Tên bộ môn

    private String description;    // Mô tả bộ môn

    private String code;           // Mã bộ môn

    private Boolean isActive;      // Trạng thái active/inactive
}

