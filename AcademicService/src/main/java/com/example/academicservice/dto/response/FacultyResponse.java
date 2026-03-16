package com.example.academicservice.dto.response;

import lombok.Data;
import java.util.UUID;

/**
 * DTO để trả về thông tin Faculty
 */
@Data
public class FacultyResponse {
    private UUID id;                  // ID của khoa
    private UUID universityId;        // ID của trường đại học
    private String universityName;    // Tên trường đại học
    private String name;              // Tên khoa
    private String slug;              // Slug của khoa
    private String code;              // Mã khoa
    private String description;       // Mô tả khoa
    private Boolean isActive;         // Trạng thái active/inactive
}

