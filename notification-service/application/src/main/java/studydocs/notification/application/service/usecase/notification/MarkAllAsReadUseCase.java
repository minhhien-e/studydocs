package studydocs.notification.application.service.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notification.application.dto.command.notification.MarkAllAsReadCommand;
import studydocs.notification.application.port.in.usecase.notification.MarkAllAsReadUseCasePort;
import studydocs.notification.application.port.out.repository.NotificationRecipientQueries;
import studydocs.notification.domain.policy.NotificationAccessPolicy;
import studydocs.notification.domain.repository.NotificationRecipientRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MarkAllAsReadUseCase implements MarkAllAsReadUseCasePort {
    private final NotificationRecipientQueries notificationQueryRepository;
    private final NotificationRecipientRepository recipientRepository;
    private final NotificationAccessPolicy notificationPolicy;

    @Override
    public Void execute(MarkAllAsReadCommand params) {
        UUID recipientId = params.recipientId();
        int batchSize = 100;
        LocalDateTime lastSeenCreatedAt = LocalDateTime.now();
        List<UUID> batch;
        
        do {
            // Get batch of unread notification IDs
            batch = notificationQueryRepository.getUnreadNotificationIdsByRecipientId(
                    recipientId, 
                    batchSize, 
                    lastSeenCreatedAt
            );
            
            if (!batch.isEmpty()) {
                // Get recipient records for this batch
                for (UUID notificationId : batch) {
                    var recipient = recipientRepository.getByNotificationIdAndRecipientId(
                            notificationId,
                            recipientId
                    );
                    
                    if (recipient != null) {
                        notificationPolicy.checkCanAccess(recipient, recipientId);
                        recipient.markAsRead();
                        recipientRepository.save(recipient);
                        lastSeenCreatedAt = recipient.getReceptionTime().value();
                    }
                }
            }
        } while (!batch.isEmpty());
        
        return null;
    }
}
