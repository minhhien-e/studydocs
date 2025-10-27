package com.example.academicservice.dto.request;

import lombok.Data;

/**
 * DTO để cập nhật thông tin Faculty
 */
@Data
public class FacultyUpdateRequest {
    private String name;              // Tên khoa
    private String description;       // Mô tả khoa
    private String code;              // Mã khoa
    private Boolean isActive;         // Trạng thái active/inactive
}

