package studydocs.notification.infrastructure.adapter.bus.handler.userprofile;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.projection.UserNotificationProfileProjection;
import studydocs.notification.application.dto.query.userprofile.GetUserNotificationProfileQuery;
import studydocs.notification.application.port.in.usecase.userprofile.GetUserNotificationProfileUseCasePort;

@Component
@RequiredArgsConstructor
public class GetUserNotificationProfileQueryHandler implements RequestHandler<BusRequestWrapper<GetUserNotificationProfileQuery>, UserNotificationProfileProjection> {
    private final GetUserNotificationProfileUseCasePort useCase;

    @Override
    public UserNotificationProfileProjection execute(BusRequestWrapper<GetUserNotificationProfileQuery> request) {
        return useCase.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return GetUserNotificationProfileQuery.class;
    }
}
