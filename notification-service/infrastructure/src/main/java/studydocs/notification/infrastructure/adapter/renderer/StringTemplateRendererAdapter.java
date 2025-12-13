package studydocs.notification.infrastructure.adapter.renderer;

import org.springframework.stereotype.Component;
import studydocs.notification.application.port.in.renderer.TemplateRenderer;

import java.util.Map;
@Component
public class StringTemplateRendererAdapter implements TemplateRenderer {
    @Override
    public String render(String template, Map<String, String> model) {
        String result = template;
        for (Map.Entry<String, String> entry : model.entrySet()) {
            result = result.replace(String.format(getTemplateFormat(),entry.getKey()), entry.getValue());
        }
        return result;
    }

    @Override
    public String getTemplateFormat() {
        return "{%s}";
    }
}
