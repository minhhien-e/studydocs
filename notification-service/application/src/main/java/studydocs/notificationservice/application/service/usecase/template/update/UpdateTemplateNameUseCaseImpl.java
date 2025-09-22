package studydocs.notificationservice.application.service.usecase.template.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.template.update.UpdateTemplateNameInput;
import studydocs.notificationservice.application.usecase.template.update.UpdateTemplateNameUseCase;
import studydocs.notificationservice.domain.model.valueobject.name.TemplateName;
import studydocs.notificationservice.domain.repository.TemplateRepositoryPort;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateTemplateNameUseCaseImpl implements UpdateTemplateNameUseCase {
    private final TemplateRepositoryPort repository;

    @Override
    public void execute(UpdateTemplateNameInput inputModel) {
        //Load dữ liệu
        var templateName = new TemplateName(inputModel.templateName());
        var newName = new TemplateName(inputModel.newName());
        var template = repository.getByName(templateName);
        // Xử lý
        template.updateName(newName);
        //Gọi repository
        repository.updateName(template);
    }
}
