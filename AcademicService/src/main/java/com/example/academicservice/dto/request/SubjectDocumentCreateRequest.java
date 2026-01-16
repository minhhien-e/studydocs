package com.example.academicservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class SubjectDocumentCreateRequest {

    @NotNull(message = "Subject ID is required")
    private UUID subjectId;

    @NotNull(message = "University ID is required")
    private UUID universityId;

    @NotNull(message = "Document ID is required")
    private UUID documentId;
}
