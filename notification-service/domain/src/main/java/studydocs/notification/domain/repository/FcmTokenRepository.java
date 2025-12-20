package studydocs.notification.domain.repository;

import io.github.domain.repository.DomainEntityRepository;
import studydocs.notification.domain.aggregate.FcmToken;

public interface FcmTokenRepository extends DomainEntityRepository<FcmToken> {
    boolean existsByValue(String token);

    void deleteByValue(String token);
}
