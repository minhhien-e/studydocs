package studydocs.notification.application.port.in.renderer;

import java.util.Collections;
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
        if (format == null || template == null) {
            return Collections.emptySet();
        }

        String[] parts = format.split("%s", -1);
        if (parts.length == 1) {
            return Collections.emptySet();
        }

        StringBuilder regex = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            regex.append(Pattern.quote(parts[i]));
            if (i < parts.length - 1) {
                regex.append("(.+?)");
            }
        }

        Pattern pattern = Pattern.compile(regex.toString(), Pattern.DOTALL);
        Matcher matcher = pattern.matcher(template);

        Set<String> keys = new HashSet<>();
        while (matcher.find()) {
            int groupCount = matcher.groupCount();
            for (int g = 1; g <= groupCount; g++) {
                String key = matcher.group(g);
                if (key != null) {
                    key = key.trim();
                    if (!key.isEmpty()) {
                        keys.add(key);
                    }
                }
            }
        }
        return keys;
    }
}