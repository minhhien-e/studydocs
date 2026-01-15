package studydocs.notification.domain.policy;

import java.util.UUID;

public interface UniqueUserProfilePolicy {
    void checkUnique(UUID userId);
}
