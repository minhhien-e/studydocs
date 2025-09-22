package studydocs.notificationservice.infrastructure.inbound.web.dto.response;

import lombok.Builder;
import studydocs.notificationservice.application.dto.output.NotificationDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record NotificationResponse(UUID id, UUID senderId, UUID recipientId,
                                   String subject, String content,
                                   boolean isRead,
                                   String type,
                                   LocalDateTime createdAt) {
    public static NotificationResponse toResponse(NotificationDto output) {
        return NotificationResponse.builder()
                .id(output.id())
                .senderId(output.senderId())
                .recipientId(output.recipientId())
                .isRead(output.isRead())
                .type(output.category())
                .subject(output.subject())
                .content(output.content())
                .createdAt(output.creationTime())
                .build();

    }
}
