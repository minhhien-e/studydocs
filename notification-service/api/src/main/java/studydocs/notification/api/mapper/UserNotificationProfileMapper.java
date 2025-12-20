package studydocs.notification.api.mapper;

import studydocs.notification.api.dto.request.userprofile.*;
import studydocs.notification.application.dto.command.userprofile.*;
import studydocs.notification.application.dto.projection.UserNotificationProfileProjection;
import studydocs.notification.application.dto.query.userprofile.GetUserNotificationProfileQuery;
import studydocs.notification.api.dto.view.NotificationPreferences;
import studydocs.notification.api.dto.view.UserNotificationProfileView;

import java.util.UUID;

public final class UserNotificationProfileMapper {

    private UserNotificationProfileMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    /// Command
    public static CreateUserNotificationProfileCommand toCommand(UUID userId, CreateUserNotificationProfileRequest request) {
        return new CreateUserNotificationProfileCommand(
                userId,
                request.getEmail(),
                request.getPhoneNumber()
        );
    }

    public static UpdateEmailCommand toCommand(UUID userId, UpdateEmailRequest request) {
        return new UpdateEmailCommand(userId, request.getNewEmail());
    }

    public static UpdatePhoneNumberCommand toCommand(UUID userId, UpdatePhoneNumberRequest request) {
        return new UpdatePhoneNumberCommand(userId, request.getNewPhoneNumber());
    }

    public static RegisterFcmTokenCommand toCommand(UUID userId, RegisterFcmTokenRequest request) {
        return new RegisterFcmTokenCommand(userId, request.getFcmToken());
    }

    public static RemoveFcmTokenCommand toCommand(RemoveFcmTokenRequest request) {
        return new RemoveFcmTokenCommand( request.getFcmToken());
    }

    public static UpdateNotificationPreferencesCommand toCommand(UUID userId, UpdateNotificationPreferencesRequest request) {
        return new UpdateNotificationPreferencesCommand(
                userId,
                request.isPushEnabled(),
                request.isEmailEnabled(),
                request.isSmsEnabled()
        );
    }

    /// Query
    public static GetUserNotificationProfileQuery toQuery(UUID userId) {
        return new GetUserNotificationProfileQuery(userId);
    }
    /// View
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
