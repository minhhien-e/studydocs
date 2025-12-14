package studydocs.notification.infrastructure.adapter.repository.userprofile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import studydocs.notification.application.dto.projection.UserNotificationProfileProjection;
import studydocs.notification.application.port.out.repository.UserNotificationProfileQueries;
import studydocs.notification.domain.exception.userprofile.UserNotificationProfileNotFoundException;
import studydocs.notification.infrastructure.mapper.UserNotificationProfileMapper;
import studydocs.notification.infrastructure.persistence.repository.UserNotificationProfileMongoRepository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserNotificationProfileReadAdapter implements UserNotificationProfileQueries {
    private final UserNotificationProfileMongoRepository mongoRepository;

    @Override
    public UserNotificationProfileProjection getByUserId(UUID userId) {
        return mongoRepository.findByUserId(userId)
                .map(UserNotificationProfileMapper::toProjection)
                .orElseThrow(() -> new UserNotificationProfileNotFoundException(userId));
    }
}
