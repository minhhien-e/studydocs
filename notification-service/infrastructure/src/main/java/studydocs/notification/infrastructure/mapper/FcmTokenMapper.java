package studydocs.notification.infrastructure.mapper;

import studydocs.notification.domain.aggregate.FcmToken;
import studydocs.notification.infrastructure.persistence.entity.FcmTokenEntity;

public final class FcmTokenMapper {
    private FcmTokenMapper() {
    }

    public static FcmToken toDomain(FcmTokenEntity entity) {
        return FcmToken.reconstruct(entity.getId(), entity.getVersion(), entity.getUserId(), entity.getValue());
    }

    public static void updateEntity(FcmTokenEntity entity, FcmToken domain) {
        if (entity.getId() == null) {
            entity.setId(domain.getId());
        }
        entity.setUserId(domain.getUserId());
        entity.setValue(domain.getValue().value());
    }
}
