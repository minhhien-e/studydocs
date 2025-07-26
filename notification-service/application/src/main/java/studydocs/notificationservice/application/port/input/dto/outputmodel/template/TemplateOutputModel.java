package studydocs.notificationservice.application.port.input.dto.outputmodel.template;

import lombok.Builder;
import lombok.Getter;
import studydocs.notificationservice.domain.entities.NotificationTemplate;
import studydocs.notificationservice.shared.enums.NotificationChannel;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class TemplateOutputModel {
    private UUID id;
    private String name;
    private String channel;
    private String subjectTemplate;
    private String bodyTemplate;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static TemplateOutputModel toOutputModel(NotificationTemplate template) {
        return TemplateOutputModel.builder()
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
