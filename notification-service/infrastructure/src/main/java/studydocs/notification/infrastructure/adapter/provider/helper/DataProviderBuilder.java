package studydocs.notification.infrastructure.adapter.provider.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataProviderBuilder {
    private final ObjectMapper objectMapper;

    public Map<String, Object> buildDataProvider(Object data, String supportPrefix) {
        if (data == null) {
            return Map.of();
        }
        Map<String, Object> fileData = objectMapper.convertValue(data, new TypeReference<>() {
        });

        return fileData.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .collect(Collectors.toMap(
                        e -> supportPrefix + "." + e.getKey(),
                        Map.Entry::getValue
                ));

    }
}
