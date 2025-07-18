package studydocs.notificationservice.shared.utils;

import java.time.LocalDateTime;

public class LocalDateUtils {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATE_TIME_FORMAT = DATE_FORMAT + " " + TIME_FORMAT;
    public static String getDateTimeFormat(String format, LocalDateTime dateTime) {
        return String.format(format, dateTime);
    }
    public static boolean isFutureDate(LocalDateTime dateTime) {
        return dateTime.isAfter(LocalDateTime.now());
    }
    public static boolean isBefore(LocalDateTime dateTime, LocalDateTime compareDateTime) {
        return dateTime.isBefore(compareDateTime);
    }
    public static boolean isAfter(LocalDateTime dateTime, LocalDateTime compareDateTime) {
        return dateTime.isAfter(compareDateTime);
    }
}
