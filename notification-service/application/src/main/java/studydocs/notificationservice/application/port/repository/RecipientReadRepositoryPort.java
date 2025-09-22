package studydocs.notificationservice.application.port.repository;

import studydocs.notificationservice.application.dto.output.UserNotificationDto;
import studydocs.notificationservice.shared.paging.SliceOutput;

import java.time.LocalDateTime;
import java.util.UUID;

public interface RecipientReadRepositoryPort {
    SliceOutput<UserNotificationDto> findByRecipientId(UUID recipientId, boolean isDeleted, LocalDateTime createdAt, int limit);
}
