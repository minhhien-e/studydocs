package studydocs.notification.api.mapper.view;

import studydocs.notification.application.dto.projection.UserNotificationProfileProjection;
import studydocs.notification.application.dto.view.NotificationPreferences;
import studydocs.notification.application.dto.view.UserNotificationProfileView;

public final class UserNotificationProfileViewMapper {
    
    private UserNotificationProfileViewMapper() {
        throw new UnsupportedOperationException("Utility class");
    }
    
    public static UserNotificationProfileView toView(UserNotificationProfileProjection projection) {
        return new UserNotificationProfileView(
                projection.id(),
                projection.fcmTokens(),
                projection.emailAddress(),
                projection.phoneNumber(),
                new NotificationPreferences(
                        projection.pushEnabled(),
                        projection.emailEnabled(),
                        projection.smsEnabled()
                )
        );
    }
}
