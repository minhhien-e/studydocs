package studydocs.notificationservice.infrastructure.inbound.web.dto.response;

import lombok.Builder;
import lombok.Getter;
import studydocs.notificationservice.application.dto.output.TemplateDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class TemplateResponse {
    private UUID id;
    private String name;
    private String channel;
    private String subjectTemplate;
    private String bodyTemplate;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static TemplateResponse toResponse(TemplateDto template) {
        return TemplateResponse.builder()
                .id(template.id())
                .name(template.name())
                .channel(template.channel())
                .subjectTemplate(template.subjectTemplate())
                .bodyTemplate(template.bodyTemplate())
                .description(template.description())
                .updatedAt(template.updatedTime())
                .build();
    }
}
