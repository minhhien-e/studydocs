package studydocs.notificationservice.application.service.usecase.template.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.template.update.EditTemplateDescriptionInput;
import studydocs.notificationservice.application.usecase.template.update.EditTemplateDescriptionUseCase;
import studydocs.notificationservice.domain.model.valueobject.name.TemplateName;
import studydocs.notificationservice.domain.repository.TemplateRepositoryPort;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateTemplateDescriptionUseCaseImpl implements EditTemplateDescriptionUseCase {
    private final TemplateRepositoryPort repository;

    @Override
    public void execute(EditTemplateDescriptionInput inputModel) {
        //Load dữ liệu
        var templateName = new TemplateName(inputModel.templateName());
        var newDescription = inputModel.newDescription();
        var template = repository.getByName(templateName);
        // Xử lý
        template.editDescription(newDescription);
        //Gọi repository
        repository.updateDescription(template);
    }
}
