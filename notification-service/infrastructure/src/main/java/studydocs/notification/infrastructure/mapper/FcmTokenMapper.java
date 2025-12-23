package studydocs.notification.infrastructure.mapper;

import studydocs.notification.domain.aggregate.FcmToken;
import studydocs.notification.infrastructure.persistence.entity.FcmTokenEntity;

public final class FcmTokenMapper {
    private FcmTokenMapper() {
    }

    public static FcmToken toDomain(FcmTokenEntity entity) {
        return FcmToken.reconstruct(entity.getId(),entity.getVersion(), entity.getUserId(), entity.getValue());
    }
    public static FcmTokenEntity toEntity(FcmToken domain) {
        return FcmTokenEntity.builder()
                .id(domain.getId())
                .version(domain.getVersion())
                .userId(domain.getUserId())
                .value(domain.getValue().value())
                .build();
    }
}
