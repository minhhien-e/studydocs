package studydocs.notification.domain.repository;

import io.github.domain.repository.AggregateRootWriter;
import studydocs.notification.domain.aggregate.FcmToken;

public interface FcmTokenRepository extends AggregateRootWriter<FcmToken> {
    boolean existsByValue(String token);

    void deleteByValue(String token);
}
