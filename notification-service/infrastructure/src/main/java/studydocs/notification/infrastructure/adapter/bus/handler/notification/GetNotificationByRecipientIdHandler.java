package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.base.CursorPaginationResult;
import studydocs.notification.application.dto.projection.NotificationRecipientProjection;
import studydocs.notification.application.dto.query.notification.GetNotificationByRecipientIdQuery;
import studydocs.notification.application.port.in.usecase.notification.GetNotificationByRecipientIdUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class GetNotificationByRecipientIdHandler
    extends AbstractHandler<GetNotificationByRecipientIdQuery, CursorPaginationResult<NotificationRecipientProjection>, GetNotificationByRecipientIdUseCasePort> {
    
    protected GetNotificationByRecipientIdHandler(GetNotificationByRecipientIdUseCasePort useCase) {
        super(useCase, GetNotificationByRecipientIdQuery.class);
    }
}
