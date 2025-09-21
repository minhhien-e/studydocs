package studydocs.notificationservice.application.dto.output;

import lombok.Builder;
import lombok.Getter;
import studydocs.notificationservice.domain.model.entity.NotificationTemplate;

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

    public static TemplateOutput toOutput(NotificationTemplate template) {
        return TemplateOutput.builder()
                .id(template.getId())
                .name(template.getName().getValue())
                .channel(template.getChannel().getChannel())
                .subjectTemplate(template.getSubjectTemplate().value())
                .bodyTemplate(template.getBodyTemplate().value())
                .description(template.getDescription().orElse(null))
                .updatedAt(template.getUpdatedTime().getValue())
                .build();
    }
}
