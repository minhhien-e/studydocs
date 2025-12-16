package studydocs.notification.application.service.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notification.application.dto.base.CursorPaginationResult;
import studydocs.notification.application.dto.projection.NotificationRecipientProjection;
import studydocs.notification.application.dto.query.notification.GetNotificationByRecipientIdQuery;
import studydocs.notification.application.port.in.usecase.notification.GetNotificationByRecipientIdUseCasePort;
import studydocs.notification.application.port.out.repository.NotificationRecipientQueries;
import studydocs.notification.application.port.out.repository.UserQueries;
import studydocs.notification.application.utils.CursorPaginationHelper;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetNotificationByRecipientIdUseCase implements GetNotificationByRecipientIdUseCasePort {
    private final NotificationRecipientQueries repository;
    private final UserQueries userQueries;

    @Override
    public CursorPaginationResult<NotificationRecipientProjection> execute(GetNotificationByRecipientIdQuery params) {
        var total = repository.countByRecipientId(params.recipientId(), params.isDeleted());

        var recipientNotifications = repository.getByRecipientId(
                params.recipientId(),
                params.isDeleted(),
                params.receivedAt(),
                params.limit() + 1
        );
        var user = userQueries.getById(params.recipientId());
        recipientNotifications.forEach(notification -> notification.getNotification().setSenderName(user.name()));

        return CursorPaginationHelper.buildResult(
                recipientNotifications,
                params.limit(),
                total,
                NotificationRecipientProjection::getReceivedAt
        );
    }
}
