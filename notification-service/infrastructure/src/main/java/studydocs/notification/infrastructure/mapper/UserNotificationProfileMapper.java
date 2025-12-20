package studydocs.notification.infrastructure.mapper;

import studydocs.notification.domain.aggregate.UserNotificationProfile;
import studydocs.notification.infrastructure.persistence.entity.UserNotificationProfileEntity;

public final class UserNotificationProfileMapper {
    
    public static UserNotificationProfileEntity toEntity(UserNotificationProfile domain) {
        return UserNotificationProfileEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .emailAddress(domain.getEmailAddress())
                .phoneNumber(domain.getPhoneNumber())
                .pushEnabled(domain.isPushEnabled())
                .emailEnabled(domain.isEmailEnabled())
                .smsEnabled(domain.isSmsEnabled())
                .build();
    }
    
    public static UserNotificationProfile toDomain(UserNotificationProfileEntity entity) {
        return UserNotificationProfile.reconstruct(
                entity.getId(),
                entity.getUserId(),
                entity.getEmailAddress(),
                entity.getPhoneNumber(),
                entity.isPushEnabled(),
                entity.isEmailEnabled(),
                entity.isSmsEnabled()
        );
    }
}
