package studydocs.notification.domain.vo;

import io.github.domain.vo.ValueObject;
import studydocs.notification.domain.exception.userprofile.InvalidEmailAddressException;

import java.util.regex.Pattern;

public class EmailAddress extends ValueObject<EmailAddress> {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

    private final String value;

    public EmailAddress(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidEmailAddressException("Email address cannot be null or empty");
        }
        if (!PATTERN.matcher(value).matches()) {
            throw new InvalidEmailAddressException("Format is invalid");
        }
        if (value.length() > 255) {
            throw new InvalidEmailAddressException("Email is too long (max 255 characters)");
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
    
    @Override
    public String toString() {
        return value;
    }
}
