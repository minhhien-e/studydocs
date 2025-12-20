package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.projection.NotificationMetadataProjection;
import studydocs.notification.application.dto.query.notification.GetNotificationMetadataQuery;
import studydocs.notification.application.port.in.usecase.base.UseCase;
import studydocs.notification.application.port.in.usecase.notification.GetNotificationMetadataUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

import java.util.List;

@Component
public class GetNotificationMetadataHandler
        extends AbstractHandler<GetNotificationMetadataQuery, List<NotificationMetadataProjection>, GetNotificationMetadataUseCasePort> {
    protected GetNotificationMetadataHandler(UseCase<List<NotificationMetadataProjection>, GetNotificationMetadataQuery> useCase) {
        super(useCase, GetNotificationMetadataQuery.class);
    }
}
