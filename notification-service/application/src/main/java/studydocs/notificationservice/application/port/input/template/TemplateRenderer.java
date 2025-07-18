package studydocs.notificationservice.application.port.input.template;

import java.util.Map;

public interface TemplateRenderer {
    String render(String template, Map<String, Object> data);
    String getTemplateFormat();
}
