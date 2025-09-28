package studydocs.notificationservice.application.service.usecase.template.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.template.update.RenameTemplateInput;
import studydocs.notificationservice.application.usecase.template.update.RenameTemplateUseCase;
import studydocs.notificationservice.domain.model.valueobject.name.TemplateName;
import studydocs.notificationservice.domain.repository.TemplateRepositoryPort;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateTemplateNameUseCaseImpl implements RenameTemplateUseCase {
    private final TemplateRepositoryPort repository;

    @Override
    public void execute(RenameTemplateInput inputModel) {
        //Load dữ liệu
        var templateName = new TemplateName(inputModel.templateName());
        var newName = new TemplateName(inputModel.newName());
        var template = repository.getByName(templateName);
        // Xử lý
        template.rename(newName);
        //Gọi repository
        repository.rename(template);
    }
}
