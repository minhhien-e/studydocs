package studydocs.notificationservice.application.port.ouput.repository;

import studydocs.notificationservice.application.port.input.dto.paging.SliceOutput;
import studydocs.notificationservice.domain.entities.NotificationRecipient;

import java.time.LocalDateTime;
import java.util.UUID;

public interface NotificationRecipientRepositoryPort {
    SliceOutput<NotificationRecipient> findByRecipientId(UUID recipientId, LocalDateTime createdAt, int pageNumber, int limit);
}
