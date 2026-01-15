package studydocs.notification.application.service.support;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.port.out.provider.NotificationDataProvider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class TemplateVariableNormalizer {
    private final List<NotificationDataProvider> dataProviders;

    public Set<String> extractVariables(String template) {
        Set<String> variables = new HashSet<>();
        if (template == null) return variables;
        for (NotificationDataProvider provider : dataProviders) {
            variables.addAll(provider.extractMetadataKey(template));
        }
        return variables;
    }

    public String normalize(String template) {
        if (template == null) return null;
        String normalized = template;
        Set<String> variables = extractVariables(template);

        for (String var : variables) {
            String regex = "(?<!\\{\\{)" + Pattern.quote(var) + "(?!\\}\\})";
            normalized = normalized.replaceAll(regex, Matcher.quoteReplacement("{{" + var + "}}"));
        }
        return normalized;
    }
}
