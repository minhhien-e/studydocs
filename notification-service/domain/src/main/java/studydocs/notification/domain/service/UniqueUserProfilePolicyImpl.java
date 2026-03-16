package studydocs.notification.domain.service;

import studydocs.notification.domain.exception.userprofile.UserNotificationProfileAlreadyExistsException;
import studydocs.notification.domain.policy.UniqueUserProfilePolicy;
import studydocs.notification.domain.repository.UserNotificationProfileRepository;

import java.util.UUID;

public class UniqueUserProfilePolicyImpl implements UniqueUserProfilePolicy {
    private final UserNotificationProfileRepository userNotificationProfileRepository;

    public UniqueUserProfilePolicyImpl(UserNotificationProfileRepository userNotificationProfileRepository) {
        this.userNotificationProfileRepository = userNotificationProfileRepository;
    }

    @Override
    public void checkUnique(UUID userId) {
        if (userNotificationProfileRepository.existsByUserId(userId)) {
            throw new UserNotificationProfileAlreadyExistsException(userId);
        }
    }
}
