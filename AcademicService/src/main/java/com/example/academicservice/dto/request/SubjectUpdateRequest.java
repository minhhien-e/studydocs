package com.example.academicservice.dto.request;

import lombok.Data;

/**
 * DTO để cập nhật thông tin Subject
 */
@Data
public class SubjectUpdateRequest {

    private String name;           // Tên môn học

    private String description;    // Mô tả môn học

    private String code;           // Mã môn học

    private Boolean isActive;      // Trạng thái active/inactive
}
