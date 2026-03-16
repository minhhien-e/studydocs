package studydocs.notification.application.dto.projection;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserProjection(UUID id, String name, String email, String fcmToken) {
}
