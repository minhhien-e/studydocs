package com.example.academicservice.dto.response;

import lombok.Data;
import java.util.UUID;

/**
 * DTO để trả về thông tin Subject
 */
@Data
public class SubjectResponse {

    private UUID id;                       // ID của môn học

    private UUID departmentId;             // ID của bộ môn

    private String departmentName;         // Tên bộ môn

    private String name;                   // Tên môn học

    private String slug;                   // Slug của môn học

    private String code;                   // Mã môn học

    private String description;            // Mô tả môn học

    private Boolean isActive;              // Trạng thái active/inactive
}
