package studydocs.config.dto;

import java.util.UUID;

public record DocumentLikedPayload(
        UUID documentId,
        UUID userId,
        UUID ownerId) {
}
