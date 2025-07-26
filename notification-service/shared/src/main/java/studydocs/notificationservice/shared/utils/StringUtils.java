package studydocs.notificationservice.shared.utils;

public final class StringUtils {
    public static boolean isNullOrBlank(final String str) {
        return str == null || str.isBlank();
    }

    public static boolean equalsIgnoreCase(final String str1, final String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }
}
