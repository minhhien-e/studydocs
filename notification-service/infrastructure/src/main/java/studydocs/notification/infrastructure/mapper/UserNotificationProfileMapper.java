package studydocs.notification.infrastructure.mapper;

import studydocs.notification.domain.aggregate.UserNotificationProfile;
import studydocs.notification.infrastructure.persistence.entity.UserNotificationProfileEntity;

public final class UserNotificationProfileMapper {
    
    public static UserNotificationProfileEntity toEntity(UserNotificationProfile domain) {
        return UserNotificationProfileEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .fcmTokens(domain.getFcmTokens())
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
                entity.getFcmTokens(),
                entity.getEmailAddress(),
                entity.getPhoneNumber(),
                entity.isPushEnabled(),
                entity.isEmailEnabled(),
                entity.isSmsEnabled()
        );
    }
    
    public static studydocs.notification.application.dto.projection.UserNotificationProfileProjection toProjection(UserNotificationProfileEntity entity) {
        return studydocs.notification.application.dto.projection.UserNotificationProfileProjection.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .fcmTokens(entity.getFcmTokens())
                .emailAddress(entity.getEmailAddress())
                .phoneNumber(entity.getPhoneNumber())
                .pushEnabled(entity.isPushEnabled())
                .emailEnabled(entity.isEmailEnabled())
                .smsEnabled(entity.isSmsEnabled())
                .build();
    }
}
