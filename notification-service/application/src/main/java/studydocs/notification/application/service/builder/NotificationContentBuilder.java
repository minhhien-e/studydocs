package studydocs.notification.application.service.builder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.payload.base.DataProvidePayload;
import studydocs.notification.application.port.in.renderer.TemplateRenderer;
import studydocs.notification.application.port.out.provider.NotificationDataProvider;
import studydocs.notification.application.service.builder.data.NotificationContent;
import studydocs.notification.application.service.support.TemplateVariableNormalizer;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class NotificationContentBuilder {
    private final TemplateRenderer templateRenderer;
    private final TemplateVariableNormalizer normalizer;
    private final Map<Class<? extends DataProvidePayload>, NotificationDataProvider<? extends DataProvidePayload>> providerMap;

    @SuppressWarnings("unchecked")
    public NotificationContent build(String subjectTemplate, String bodyTemplate, DataProvidePayload payload) {
        Map<String, String> model = new HashMap<>();
        var provider = (NotificationDataProvider<DataProvidePayload>) providerMap.get(payload.getClass());
        Map<String, Object> data = provider.getData(payload);
        data.forEach((key, value) -> model.put(key, String.valueOf(value)));

        String renderedSubject = templateRenderer.render(normalizer.normalize(subjectTemplate), model);
        String renderedBody = templateRenderer.render(normalizer.normalize(bodyTemplate), model);

        return new NotificationContent(renderedSubject, renderedBody);
    }
}
