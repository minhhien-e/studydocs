package studydocs.notification.domain.repository;

import io.github.domain.repository.DomainEntityRepository;
import studydocs.notification.domain.aggregate.UserNotificationProfile;

import java.util.List;
import java.util.UUID;

/**
 * Domain repository for UserNotificationProfile aggregate.
 * Command side repository - for write operations.
 */
public interface UserNotificationProfileRepository extends DomainEntityRepository<UserNotificationProfile> {
    /**
     * Finds profile by userId (reference to user in User bounded context).
     */
    UserNotificationProfile getByUserId(UUID userId);

    /**
     * Checks if a profile exists for the given user.
     */
    boolean existsByUserId(UUID userId);

    boolean existsAllByUserIdIn(List<UUID> userIds);

}
