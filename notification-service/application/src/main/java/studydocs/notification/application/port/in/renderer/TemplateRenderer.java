package studydocs.notification.application.port.in.renderer;

import java.util.Map;

public interface TemplateRenderer {
    String render(String template, Map<String, String> model);

    String getTemplateFormat();
}