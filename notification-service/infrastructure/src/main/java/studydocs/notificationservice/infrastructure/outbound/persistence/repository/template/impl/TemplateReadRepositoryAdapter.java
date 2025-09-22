package studydocs.notificationservice.infrastructure.outbound.persistence.repository.template.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import studydocs.notificationservice.application.dto.output.TemplateDto;
import studydocs.notificationservice.application.port.repository.TemplateReadRepositoryPort;
import studydocs.notificationservice.infrastructure.outbound.persistence.entity.TemplateDocument;
import studydocs.notificationservice.infrastructure.outbound.persistence.mapper.TemplateMapper;
import studydocs.notificationservice.infrastructure.outbound.persistence.repository.template.TemplateMongoRepository;
import studydocs.notificationservice.shared.exception.infrastructure.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

import static studydocs.notificationservice.infrastructure.outbound.persistence.mapper.TemplateMapper.toDto;

@Repository
@RequiredArgsConstructor
public class TemplateReadRepositoryAdapter implements TemplateReadRepositoryPort {
    private final TemplateMongoRepository notificationTemplateMongoRepository;
    private final static String RESOURCE_TYPE = "mẫu thông báo";

    @Override
    public TemplateDto getByName(String name) {
        TemplateDocument document = notificationTemplateMongoRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException(RESOURCE_TYPE));
        return toDto(document);
    }

    @Override
    public TemplateDto getById(UUID id) {
        TemplateDocument document = notificationTemplateMongoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURCE_TYPE));
        return toDto(document);
    }

    @Override
    public List<TemplateDto> findAll() {
        return notificationTemplateMongoRepository.findAll().stream().map(TemplateMapper::toDto).toList();
    }

    @Override
    public List<TemplateDto> searchByName(String name) {
        return notificationTemplateMongoRepository.searchByNameLikeIgnoreCase(name).stream().map(TemplateMapper::toDto).toList();
    }

    @Override
    public List<TemplateDto> findAllByChannel(String channel) {
        return notificationTemplateMongoRepository.findAllByChannel(channel).stream().map(TemplateMapper::toDto).toList();
    }
}
