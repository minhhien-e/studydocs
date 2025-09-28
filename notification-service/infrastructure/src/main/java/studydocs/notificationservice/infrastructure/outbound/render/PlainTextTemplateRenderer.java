package studydocs.notificationservice.infrastructure.outbound.render;

import org.springframework.stereotype.Component;
import studydocs.notificationservice.application.port.render.TemplateRenderer;

import java.util.Map;

@Component("plainTextTemplateRenderer")
public class PlainTextTemplateRenderer implements TemplateRenderer {
    @Override
    public String render(String template, Map<String, Object> data) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String placeholder = String.format(getTemplateFormat(), entry.getKey());
            template = template.replace(placeholder, entry.getValue().toString());
        }
        return template;
    }

    @Override
    public String getTemplateFormat() {
        return "<<%s>>";
    }
}
