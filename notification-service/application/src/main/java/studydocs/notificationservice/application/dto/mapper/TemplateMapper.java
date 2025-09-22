package studydocs.notificationservice.application.dto.mapper;

import studydocs.notificationservice.application.dto.output.TemplateDto;
import studydocs.notificationservice.domain.model.entity.Template;

public class TemplateMapper {
    public static Template toDomain(TemplateDto dto) {
        return new Template(dto.id(), dto.name(), dto.channel(), dto.subjectTemplate(), dto.bodyTemplate(), dto.description(), dto.creationTime(), dto.updatedTime());
    }
}
