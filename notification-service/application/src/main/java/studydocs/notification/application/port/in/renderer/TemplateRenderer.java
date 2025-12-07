package studydocs.notification.application.port.in.renderer;

import java.util.Map;

public interface TemplateRenderer {
    String render(String templateName, Map<String, String> model);

    String getTemplateFormat();
}
