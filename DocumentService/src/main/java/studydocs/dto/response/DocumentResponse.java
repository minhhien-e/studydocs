package studydocs.dto.response;

import studydocs.domain.Document;

import java.time.LocalDateTime;
import java.util.UUID;

public record DocumentResponse(
        UUID id,
        UUID userId,
        String title,
        String description,
        UUID fileId,
        String status,
        boolean isDeleted,
        String schoolYear,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String downloadUrl,
        Object previewDataView,
        UUID subjectId,
        UUID universityId,
        Integer totalPages) { // Added totalPages
    public DocumentResponse(Document doc) {
        this(
                doc.getId(),
                doc.getUserId(),
                doc.getTitle(),
                doc.getDescription(),
                doc.getFileId(),
                doc.getStatus().name(),
                doc.getIsDeleted(),
                doc.getSchoolYear(),
                doc.getCreatedAt(),
                doc.getUpdatedAt(),
                null,
                null,
                null,
                null,
                0); // Default 0
    }

    public DocumentResponse(Document doc, String downloadUrl, Object previewDataView) {
        this(
                doc.getId(),
                doc.getUserId(),
                doc.getTitle(),
                doc.getDescription(),
                doc.getFileId(),
                doc.getStatus().name(),
                doc.getIsDeleted(),
                doc.getSchoolYear(),
                doc.getCreatedAt(),
                doc.getUpdatedAt(),
                downloadUrl,
                previewDataView,
                null,
                null,
                0);
    }

    public DocumentResponse(DocumentResponse original, UUID subjectId, UUID universityId) {
        this(
                original.id(),
                original.userId(),
                original.title(),
                original.description(),
                original.fileId(),
                original.status(),
                original.isDeleted(),
                original.schoolYear(),
                original.createdAt(),
                original.updatedAt(),
                original.downloadUrl(),
                original.previewDataView(),
                subjectId,
                universityId,
                original.totalPages());
    }

    public DocumentResponse(DocumentResponse original, String downloadUrl, Object previewDataView, Integer totalPages) {
        this(
                original.id(),
                original.userId(),
                original.title(),
                original.description(),
                original.fileId(),
                original.status(),
                original.isDeleted(),
                original.schoolYear(),
                original.createdAt(),
                original.updatedAt(),
                downloadUrl,
                previewDataView,
                original.subjectId(),
                original.universityId(),
                totalPages != null ? totalPages : original.totalPages());
    }
}