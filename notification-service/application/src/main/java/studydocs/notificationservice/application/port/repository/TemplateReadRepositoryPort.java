package studydocs.notificationservice.application.port.repository;

import studydocs.notificationservice.application.dto.output.TemplateDto;

import java.util.List;
import java.util.UUID;

public interface TemplateReadRepositoryPort {
    TemplateDto getByName(String name);

    TemplateDto getById(UUID id);

    List<TemplateDto> findAll();

    List<TemplateDto> searchByName(String name);

    List<TemplateDto> findAllByChannel(String channel);
}
