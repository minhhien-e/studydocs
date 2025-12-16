package studydocs.notification.application.service.usecase.userprofile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notification.application.dto.projection.UserNotificationProfileProjection;
import studydocs.notification.application.dto.query.userprofile.GetUserNotificationProfileQuery;
import studydocs.notification.application.port.in.usecase.userprofile.GetUserNotificationProfileUseCasePort;
import studydocs.notification.application.port.out.repository.UserNotificationProfileQueries;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetUserNotificationProfileUseCase implements GetUserNotificationProfileUseCasePort {
    private final UserNotificationProfileQueries queries;

    @Override
    public UserNotificationProfileProjection execute(GetUserNotificationProfileQuery query) {
        return queries.getByUserId(query.userId());
    }
}
