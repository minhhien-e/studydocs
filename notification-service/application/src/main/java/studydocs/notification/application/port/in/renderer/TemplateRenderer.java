package studydocs.notification.application.port.in.renderer;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface TemplateRenderer {
    String render(String template, Map<String, String> model);

    String getTemplateFormat();

    default Set<String> getModelKeys(String template) {
        String format = getTemplateFormat();
        String regex = Pattern.quote(format).replace("%s", "(.*?)");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(template);

        Set<String> keys = new HashSet<>();
        while (matcher.find()) {
            keys.add(matcher.group(1));
        }
        return keys;
    }
}
