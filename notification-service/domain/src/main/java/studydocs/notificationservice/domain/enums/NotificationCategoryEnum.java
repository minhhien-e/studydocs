package studydocs.notificationservice.domain.enums;

import java.util.Arrays;

public enum NotificationCategoryEnum {
    SYSTEM,
    NEW_DOCUMENT,
    REPLY_COMMENT,
    LIKE_COMMENT;

    public static boolean contains(String value) {
        for (NotificationCategoryEnum category : NotificationCategoryEnum.values()) {
            if (category.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    public static String getValues() {
        return Arrays.stream(NotificationCategoryEnum.values()).map(Enum::name).toList().toString();
    }
}
