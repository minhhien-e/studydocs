package studydocs.notification.domain.aggregate;

import io.github.domain.aggregate.base.AggregateRoot;
import studydocs.notification.domain.exception.userprofile.DuplicateFcmTokenException;
import studydocs.notification.domain.vo.FcmToken;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserNotificationProfile extends AggregateRoot {
    private UUID userId;

    private List<FcmToken> fcmTokens;
    private String emailAddress;
    private String phoneNumber;

    private boolean pushEnabled;
    private boolean emailEnabled;
    private boolean smsEnabled;

    private UserNotificationProfile(UUID id) {
        super(id);
    }

    private UserNotificationProfile() {
        super();
    }

    /// Business Logic

    public void registerFcmToken(String tokenValue) {
        var token = new FcmToken(tokenValue);

        if (fcmTokens.stream().anyMatch(t -> t.value().equals(tokenValue))) {
            throw new DuplicateFcmTokenException(tokenValue);
        }

        this.fcmTokens.add(token);
        markChanged("fcmTokens");
    }

    public void removeFcmToken(String tokenValue) {
        boolean removed = this.fcmTokens.removeIf(t -> t.value().equals(tokenValue));
        if (removed) {
            markChanged("fcmTokens");
        }
    }

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
        profile.fcmTokens = new ArrayList<>();

        // Default preferences - all enabled
        profile.pushEnabled = true;
        profile.emailEnabled = true;
        profile.smsEnabled = true;

        return profile;
    }

    public static UserNotificationProfile reconstruct(
            UUID id,
            UUID userId,
            List<String> fcmTokens,
            String email,
            String phone,
            boolean pushEnabled,
            boolean emailEnabled,
            boolean smsEnabled
    ) {
        var profile = new UserNotificationProfile(id);
        profile.userId = userId;
        profile.fcmTokens = fcmTokens != null
                ? new ArrayList<>(fcmTokens.stream().map(FcmToken::new).toList())
                : new ArrayList<>();
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

    public List<String> getFcmTokens() {
        return fcmTokens.stream().map(FcmToken::value).toList();
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
