package studydocs.notification.domain.vo;

import io.github.domain.vo.ValueObject;
import studydocs.notification.domain.exception.userprofile.InvalidFcmTokenException;

public class FcmTokenValue extends ValueObject<FcmTokenValue> {
    private final String value;

    public FcmTokenValue(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidFcmTokenException("token cannot be null or empty");
        }
        if (value.length() > 500) {
            throw new InvalidFcmTokenException("token is too long (max 500 characters)");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    protected Object[] getEqualityComponents() {
        return new Object[]{value};
    }
}
