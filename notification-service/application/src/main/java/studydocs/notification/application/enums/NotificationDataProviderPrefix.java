package studydocs.notification.application.enums;

import lombok.Getter;

@Getter
public enum NotificationDataProviderPrefix {
    FILE("$file"),
    USER("$user");
    private final String prefix;
    NotificationDataProviderPrefix(String prefix) {
        this.prefix = prefix;
    }
}
