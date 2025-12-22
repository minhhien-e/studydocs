package studydocs.notification.application.service.builder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.port.in.renderer.TemplateRenderer;
import studydocs.notification.application.port.out.provider.NotificationDataProvider;
import studydocs.notification.application.service.builder.data.TemplateContent;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TemplateContentBuilder {
    private final List<NotificationDataProvider<?>> dataProviders;
    private final TemplateRenderer renderer;

    public TemplateContent build(String subject, String body) {
        String templateFormat = renderer.getTemplateFormat();
        for (NotificationDataProvider<?> dataProvider : dataProviders) {
            if (subject != null) {
                var metadataKeys = dataProvider.extractMetadataKey(subject);
                if (!metadataKeys.isEmpty()) {
                    subject = replaceKeysInText(subject, metadataKeys, templateFormat);
                }
            }
            if (body != null) {
                var metadataKeys = dataProvider.extractMetadataKey(body);
                if (!metadataKeys.isEmpty()) {
                    body = replaceKeysInText(body, metadataKeys, templateFormat);
                }
            }

        }
        return new TemplateContent(subject, body);
    }

    private String replaceKeysInText(String text, Collection<String> keys, String templateFormat) {
        if (text == null || keys == null || keys.isEmpty()) return text;

        List<String> sortedKeys = keys.stream()
                .distinct()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .toList();

        String result = text;
        for (String key : sortedKeys) {
            if (key == null || key.isEmpty()) continue;

            String formatted = String.format(templateFormat, key);

            if (result.contains(formatted)) continue;

            result = result.replace(key, formatted);
        }
        return result;
    }
}
