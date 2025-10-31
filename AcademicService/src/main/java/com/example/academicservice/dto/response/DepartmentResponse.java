package com.example.academicservice.dto.response;

import lombok.Data;

/**
 * DTO để trả về thông tin Department
 */
@Data
public class DepartmentResponse {

    private Long id;                   // ID của bộ môn

    private Long facultyId;            // ID của khoa

    private String facultyName;        // Tên khoa

    private String name;               // Tên bộ môn

    private String slug;               // Slug của bộ môn

    private String code;               // Mã bộ môn

    private String description;        // Mô tả bộ môn

    private Boolean isActive;          // Trạng thái active/inactive
}

