package studydocs.notificationservice.domain.enums;

import java.util.Arrays;

public enum NotificationChannel {
    EMAIL, SMS, IN_APP, PUSH;

    public static boolean contains(String channel) {
        for (NotificationChannel notificationChannel : NotificationChannel.values()) {
            if (notificationChannel.name().equalsIgnoreCase(channel)) {
                return true;
            }
        }
        return false;
    }

    public static String getValues() {
        return Arrays.stream(NotificationChannel.values()).map(Enum::name).toList().toString();
    }
}