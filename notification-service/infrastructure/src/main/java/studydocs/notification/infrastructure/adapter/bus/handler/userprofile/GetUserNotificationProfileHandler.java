package studydocs.notification.infrastructure.adapter.bus.handler.userprofile;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.projection.UserNotificationProfileProjection;
import studydocs.notification.application.dto.query.userprofile.GetUserNotificationProfileQuery;
import studydocs.notification.application.port.in.usecase.userprofile.GetUserNotificationProfileUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class GetUserNotificationProfileHandler
    extends AbstractHandler<GetUserNotificationProfileQuery, UserNotificationProfileProjection> {
    
    protected GetUserNotificationProfileHandler(GetUserNotificationProfileUseCasePort useCase) {
        super(useCase, GetUserNotificationProfileQuery.class);
    }
}
