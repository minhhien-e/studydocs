package studydocs.notificationservice.application.service.usecase.template.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.template.read.GetAllTemplateInput;
import studydocs.notificationservice.application.dto.output.TemplateDto;
import studydocs.notificationservice.application.port.repository.TemplateReadRepositoryPort;
import studydocs.notificationservice.application.usecase.template.read.GetAllTemplateUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GetAllTemplateUseCaseImpl implements GetAllTemplateUseCase {
    private final TemplateReadRepositoryPort repository;

    @Override
    public List<TemplateDto> execute(GetAllTemplateInput inputModel) {
        return repository.findAll();
    }
}
