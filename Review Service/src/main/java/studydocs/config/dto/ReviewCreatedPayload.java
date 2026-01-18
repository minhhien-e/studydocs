package studydocs.config.dto;

import java.util.UUID;

public record ReviewCreatedPayload(
    UUID reviewId,
    UUID documentId,
    UUID userId,
    String comment
) {}
