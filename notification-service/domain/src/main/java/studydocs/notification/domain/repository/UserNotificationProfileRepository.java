package studydocs.notification.domain.repository;

import io.github.domain.repository.AggregateRootWriter;
import studydocs.notification.domain.aggregate.UserNotificationProfile;

import java.util.List;
import java.util.UUID;

public interface UserNotificationProfileRepository extends AggregateRootWriter<UserNotificationProfile> {
    UserNotificationProfile getByUserId(UUID userId);

    boolean existsByUserId(UUID userId);

    boolean existsAllByUserIdIn(List<UUID> userIds);

}
