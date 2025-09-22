package studydocs.notificationservice.domain.enums;

import java.util.Arrays;

public enum NotificationChannelEnum {
    EMAIL, SMS, IN_APP, PUSH;

    public static boolean contains(String channel) {
        for (NotificationChannelEnum notificationChannel : NotificationChannelEnum.values()) {
            if (notificationChannel.name().equalsIgnoreCase(channel)) {
                return true;
            }
        }
        return false;
    }

    public static String getValues() {
        return Arrays.stream(NotificationChannelEnum.values()).map(Enum::name).toList().toString();
    }
}