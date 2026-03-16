package studydocs.notification.application.port.in.usecase.notification;

import studydocs.notification.application.dto.projection.NotificationRecipientProjection;

import studydocs.notification.application.dto.base.CursorPaginationResult;
import studydocs.notification.application.dto.query.notification.GetNotificationByRecipientIdQuery;
import studydocs.notification.application.port.in.usecase.base.UseCase;

public interface GetNotificationByRecipientIdUseCasePort extends UseCase<CursorPaginationResult<NotificationRecipientProjection>, GetNotificationByRecipientIdQuery> {
}
