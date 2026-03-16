package studydocs.notification.domain.vo;

import io.github.domain.vo.ValueObject;
import studydocs.notification.domain.exception.userprofile.InvalidPhoneNumberException;

import java.util.regex.Pattern;

public class PhoneNumber extends ValueObject<PhoneNumber> {
    // Simple regex for phone number: allows digits, spaces, plus, dashes, parentheses.
    // Must contain at least 7 digits.
    private static final String PHONE_REGEX = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";
    private static final Pattern PATTERN = Pattern.compile(PHONE_REGEX);

    private final String value;

    public PhoneNumber(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidPhoneNumberException("Phone number cannot be null or empty");
        }
        if (!PATTERN.matcher(value).matches()) {
            throw new InvalidPhoneNumberException("Format is invalid");
        }
        // Count digits
        long digits = value.chars().filter(Character::isDigit).count();
        if (digits < 7 || digits > 15) {
             throw new InvalidPhoneNumberException("Phone number must have between 7 and 15 digits");
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
