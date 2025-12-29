package studydocs.notification.domain.aggregate;


import io.github.domain.aggregate.AggregateRoot;
import studydocs.notification.domain.vo.EmailAddress;
import studydocs.notification.domain.vo.PhoneNumber;

import java.util.UUID;

public class UserNotificationProfile extends AggregateRoot {
    private UUID userId;

    private EmailAddress emailAddress;
    private PhoneNumber phoneNumber;

    private boolean pushEnabled;
    private boolean emailEnabled;
    private boolean smsEnabled;

    private UserNotificationProfile(UUID id, long version) {
        super(id,version);
    }

    private UserNotificationProfile() {
        super();
    }

    /// Business Logic

    public void updateEmail(String email) {
        this.emailAddress = new EmailAddress(email);
    }

    public void updatePhoneNumber(String phone) {
        this.phoneNumber = new PhoneNumber(phone);
    }

    public void setPushEnabled(boolean enabled) {
        this.pushEnabled = enabled;
    }

    public void setEmailEnabled(boolean enabled) {
        this.emailEnabled = enabled;
    }

    public void setSmsEnabled(boolean enabled) {
        this.smsEnabled = enabled;
    }

    /// Factory Methods

    public static UserNotificationProfile create(
            UUID userId,
            String email,
            String phoneNumber
    ) {
        var profile = new UserNotificationProfile();
        profile.userId = userId;
        profile.emailAddress = new EmailAddress(email);
        profile.phoneNumber = new PhoneNumber(phoneNumber);
        // Default preferences - all enabled
        profile.pushEnabled = true;
        profile.emailEnabled = true;
        profile.smsEnabled = true;

        return profile;
    }

    public static UserNotificationProfile reconstruct(
            UUID id, long version,
            UUID userId,
            String email,
            String phone,
            boolean pushEnabled,
            boolean emailEnabled,
            boolean smsEnabled
    ) {
        var profile = new UserNotificationProfile(id,version);
        profile.userId = userId;
        profile.emailAddress = new EmailAddress(email);
        profile.phoneNumber = new PhoneNumber(phone);
        profile.pushEnabled = pushEnabled;
        profile.emailEnabled = emailEnabled;
        profile.smsEnabled = smsEnabled;

        return profile;
    }

    /// Getters

    public UUID getUserId() {
        return userId;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isPushEnabled() {
        return pushEnabled;
    }

    public boolean isEmailEnabled() {
        return emailEnabled;
    }

    public boolean isSmsEnabled() {
        return smsEnabled;
    }
}
