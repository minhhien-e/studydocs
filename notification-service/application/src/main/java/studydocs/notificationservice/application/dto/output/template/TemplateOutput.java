package studydocs.notificationservice.application.dto.output.template;

import lombok.Builder;
import lombok.Getter;
import studydocs.notificationservice.domain.entity.NotificationTemplate;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class TemplateOutput {
    private UUID id;
    private String name;
    private String channel;
    private String subjectTemplate;
    private String bodyTemplate;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static TemplateOutput toOutputModel(NotificationTemplate template) {
        return TemplateOutput.builder()
                .id(template.getId())
                .name(template.getName().getValue())
                .channel(template.getChannel().getChannel())
                .subjectTemplate(template.getSubjectTemplate().value())
                .bodyTemplate(template.getBodyTemplate().value())
                .description(template.getDescription().orElse(null))
                .updatedAt(template.getUpdatedAt().getValue())
                .build();
    }
}
