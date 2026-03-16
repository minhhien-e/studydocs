package studydocs.notification.infrastructure.dto.integration;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserIntegration(
        UUID id,
        String name
) {
}