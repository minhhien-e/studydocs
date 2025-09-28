package studydocs.notificationservice.application.service.usecase.template.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.template.update.EditTemplateBodyInput;
import studydocs.notificationservice.application.usecase.template.update.EditTemplateBodyUseCase;
import studydocs.notificationservice.domain.model.valueobject.name.TemplateName;
import studydocs.notificationservice.domain.model.valueobject.template.TemplateBody;
import studydocs.notificationservice.domain.repository.TemplateRepositoryPort;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateTemplateBodyUseCaseImpl implements EditTemplateBodyUseCase {
    private final TemplateRepositoryPort repository;

    @Override
    public void execute(EditTemplateBodyInput inputModel) {
        //Load dữ liệu
        var templateName = new TemplateName(inputModel.templateName());
        var newBody = new TemplateBody(inputModel.newBody());
        var template = repository.getByName(templateName);
        // Xử lý
        template.editBody(newBody);
        //Gọi repository
        repository.updateBody(template);
    }
}
