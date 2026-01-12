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
        LocalDateTime updatedAt) {
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
                doc.getUpdatedAt());
    }
}