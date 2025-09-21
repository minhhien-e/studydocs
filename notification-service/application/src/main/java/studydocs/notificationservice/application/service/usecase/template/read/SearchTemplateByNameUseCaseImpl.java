package studydocs.notificationservice.application.service.usecase.template.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.template.read.SearchTemplateByNameInput;
import studydocs.notificationservice.application.dto.output.TemplateOutput;
import studydocs.notificationservice.application.usecase.template.read.SearchTemplateByNameUseCase;
import studydocs.notificationservice.domain.repository.NotificationTemplateRepositoryPort;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SearchTemplateByNameUseCaseImpl implements SearchTemplateByNameUseCase {
    private final NotificationTemplateRepositoryPort repository;

    @Override
    public List<TemplateOutput> execute(SearchTemplateByNameInput inputModel) {
        return repository.searchByName(inputModel.getName())
                .stream().map(TemplateOutput::toOutput).toList();
    }
}
