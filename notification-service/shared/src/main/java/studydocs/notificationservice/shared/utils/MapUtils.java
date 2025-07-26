package studydocs.notificationservice.shared.utils;

import java.util.Map;
import java.util.Objects;

public final class MapUtils {
    public static boolean isNullOrEmpty(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean hasNullValues(final Map<?, ?> map) {
        return map.values().stream().anyMatch(Objects::isNull);
    }

    public static boolean hasNullKeys(final Map<?, ?> map) {
        return map.keySet().stream().anyMatch(Objects::isNull);
    }
}
