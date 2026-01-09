package com.example.academicservice.dto.request;

import lombok.Data;

/**
 * DTO để cập nhật thông tin Major
 */
@Data
public class MajorUpdateRequest {

    private String name;           // Tên ngành

    private String description;    // Mô tả ngành

    private String code;           // Mã ngành

    private Boolean isActive;      // Trạng thái active/inactive
}

