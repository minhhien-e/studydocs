package studydocs.notification.application.port.out.provider;

import studydocs.notification.application.dto.payload.base.DataProvidePayload;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public interface NotificationDataProvider<Payload extends DataProvidePayload> {
    String getSupportPrefix();

    String getGroupName();

    Map<String, Object> getData(Payload payload);

    Map<String, String> getAvailableMetadata();

    default List<String> extractMetadataKey(String content) {
        if (content == null || content.isBlank()) {
            return List.of();
        }

        String prefix = Pattern.quote(getSupportPrefix());

        String segment = "[A-Za-z0-9_\\-]+";


        Pattern pattern = Pattern.compile("(?<![A-Za-z0-9_.-])(" + prefix + "(?:\\." + segment + ")+)");
        Matcher matcher = pattern.matcher(content);
        var found = new LinkedHashSet<String>();

        while (matcher.find()) {
            found.add(matcher.group(1));
        }

        return new ArrayList<>(found);
    }

}
