package studydocs.notificationservice.infrastructure.outbound.persistence.mapper;

import studydocs.notificationservice.application.dto.output.TemplateDto;
import studydocs.notificationservice.domain.model.entity.Template;
import studydocs.notificationservice.infrastructure.outbound.persistence.entity.TemplateDocument;

public final class TemplateMapper {
    public static TemplateDocument toDocument(Template domain) {
        return TemplateDocument.builder().id(domain.getId()).name(domain.getName().getValue()).channel(domain.getChannel().getValue()).subjectTemplate(domain.getSubjectTemplate().value()).bodyTemplate(domain.getBodyTemplate().value()).description(domain.getDescription()).createdAt(domain.getCreationTime().getValue()).build();
    }

    public static Template toDomain(TemplateDocument document) {
        return new Template(document.getId(), document.getName(), document.getChannel(), document.getSubjectTemplate(), document.getBodyTemplate(), document.getDescription(), document.getCreatedAt(), document.getUpdatedTime());
    }

    public static TemplateDto toDto(TemplateDocument document) {
        return TemplateDto.builder().id(document.getId()).name(document.getName()).channel(document.getChannel()).subjectTemplate(document.getSubjectTemplate()).bodyTemplate(document.getBodyTemplate()).description(document.getDescription()).creationTime(document.getCreatedAt()).updatedTime(document.getUpdatedTime()).build();
    }
}
