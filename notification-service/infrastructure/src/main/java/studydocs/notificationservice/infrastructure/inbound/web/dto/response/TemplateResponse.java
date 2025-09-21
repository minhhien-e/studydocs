package studydocs.notificationservice.infrastructure.inbound.web.dto.response;

import lombok.Builder;
import lombok.Getter;
import studydocs.notificationservice.application.dto.output.TemplateOutput;

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

    public static TemplateResponse toResponse(TemplateOutput template) {
        return TemplateResponse.builder()
                .id(template.getId())
                .name(template.getName())
                .channel(template.getChannel())
                .subjectTemplate(template.getSubjectTemplate())
                .bodyTemplate(template.getBodyTemplate())
                .description(template.getDescription())
                .updatedAt(template.getUpdatedAt())
                .build();
    }
}
