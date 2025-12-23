package studydocs.notification.domain.aggregate;

import io.github.domain.aggregate.base.AggregateRoot;

import java.util.List;
import java.util.UUID;

public class UserNotificationProfile extends AggregateRoot {
    private UUID userId;

    private String emailAddress;
    private String phoneNumber;

    private boolean pushEnabled;
    private boolean emailEnabled;
    private boolean smsEnabled;

    private UserNotificationProfile(UUID id, Integer version) {
        super(id,version);
    }

    private UserNotificationProfile() {
        super();
    }

    /// Business Logic

    public void updateEmail(String email) {
        this.emailAddress = email;
        markChanged("emailAddress");
    }

    public void updatePhoneNumber(String phone) {
        this.phoneNumber = phone;
        markChanged("phoneNumber");
    }

    public void setPushEnabled(boolean enabled) {
        this.pushEnabled = enabled;
        markChanged("pushEnabled");
    }

    public void setEmailEnabled(boolean enabled) {
        this.emailEnabled = enabled;
        markChanged("emailEnabled");
    }

    public void setSmsEnabled(boolean enabled) {
        this.smsEnabled = enabled;
        markChanged("smsEnabled");
    }

    /// Factory Methods

    public static UserNotificationProfile create(
            UUID userId,
            String email,
            String phoneNumber
    ) {
        var profile = new UserNotificationProfile();
        profile.userId = userId;
        profile.emailAddress = email;
        profile.phoneNumber = phoneNumber;
        // Default preferences - all enabled
        profile.pushEnabled = true;
        profile.emailEnabled = true;
        profile.smsEnabled = true;

        return profile;
    }

    public static UserNotificationProfile reconstruct(
            UUID id, Integer version,
            UUID userId,
            String email,
            String phone,
            boolean pushEnabled,
            boolean emailEnabled,
            boolean smsEnabled
    ) {
        var profile = new UserNotificationProfile(id,version);
        profile.userId = userId;
        profile.emailAddress = email;
        profile.phoneNumber = phone;
        profile.pushEnabled = pushEnabled;
        profile.emailEnabled = emailEnabled;
        profile.smsEnabled = smsEnabled;

        return profile;
    }

    /// Getters

    public UUID getUserId() {
        return userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
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
