package com.example.academicservice.dto.request;

import lombok.Data;
import java.util.UUID;

/**
 * DTO để tạo mới Major
 */
@Data
public class MajorCreateRequest {

    private UUID departmentId;     // ID của bộ môn

    private String name;           // Tên ngành

    private String description;    // Mô tả ngành

    private String code;           // Mã ngành
}

