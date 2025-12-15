package studydocs.notification.domain.vo;

import studydocs.notification.domain.exception.userprofile.InvalidFcmTokenException;

public record FcmToken(String value) {
    public FcmToken {
        if (value == null || value.isBlank()) {
            throw new InvalidFcmTokenException("token cannot be null or empty");
        }
        if (value.length() > 500) {
            throw new InvalidFcmTokenException("token is too long (max 500 characters)");
        }
    }
}
