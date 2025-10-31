package com.example.academicservice.dto.response;

import lombok.Data;

/**
 * DTO để trả về thông tin Major
 */
@Data
public class MajorResponse {

    private Long id;                       // ID của ngành

    private Long departmentId;             // ID của bộ môn

    private String departmentName;         // Tên bộ môn

    private String name;                   // Tên ngành

    private String slug;                   // Slug của ngành

    private String code;                   // Mã ngành

    private String description;            // Mô tả ngành

    private Boolean isActive;              // Trạng thái active/inactive
}

