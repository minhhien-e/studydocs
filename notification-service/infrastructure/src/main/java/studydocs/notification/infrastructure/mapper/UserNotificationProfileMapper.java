package studydocs.notification.infrastructure.mapper;

import studydocs.notification.domain.aggregate.UserNotificationProfile;
import studydocs.notification.infrastructure.persistence.entity.UserNotificationProfileEntity;

public final class UserNotificationProfileMapper {
    
    public static UserNotificationProfile toDomain(UserNotificationProfileEntity entity) {
        return UserNotificationProfile.reconstruct(
                entity.getId(),
                entity.getVersion(),
                entity.getUserId(),
                entity.getEmailAddress(),
                entity.getPhoneNumber(),
                entity.isPushEnabled(),
                entity.isEmailEnabled(),
                entity.isSmsEnabled()
        );
    }

    public static void updateEntity(UserNotificationProfileEntity entity, UserNotificationProfile domain) {
        if(entity.getId() == null) {
            entity.setId(domain.getId());
        }
        entity.setUserId(domain.getUserId());
        entity.setEmailAddress(domain.getEmailAddress());
        entity.setPhoneNumber(domain.getPhoneNumber());
        entity.setPushEnabled(domain.isPushEnabled());
        entity.setEmailEnabled(domain.isEmailEnabled());
        entity.setSmsEnabled(domain.isSmsEnabled());
    }
}
