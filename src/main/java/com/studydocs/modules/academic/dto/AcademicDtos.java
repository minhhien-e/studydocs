package com.studydocs.modules.academic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AcademicDtos {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UniversityDto {
        private Long id;
        private String code;
        private String name;
        private String englishName;
        private String logoUrl;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubjectDto {
        private Long id;
        private String code;
        private String name;
        private Long universityId;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FacultyDto {
        private Long id;
        private String code;
        private String name;
        private String slug;
        private String description;
        private Long universityId;
        private Boolean isActive;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DepartmentDto {
        private Long id;
        private String code;
        private String name;
        private String slug;
        private String description;
        private Long facultyId;
        private Boolean isActive;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DocumentCreateDto {
        @NotBlank(message = "Title is required")
        private String title;
        private String description;
        private String category;
        private String school;
        private Integer pageCount;
        private String year;
        private Long subjectId;
        private Long universityId;
    }
}
