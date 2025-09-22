package studydocs.notificationservice.application.service.usecase.template.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.template.update.UpdateTemplateBodyInput;
import studydocs.notificationservice.application.usecase.template.update.UpdateTemplateBodyUseCase;
import studydocs.notificationservice.domain.model.valueobject.name.TemplateName;
import studydocs.notificationservice.domain.model.valueobject.template.TemplateBody;
import studydocs.notificationservice.domain.repository.TemplateRepositoryPort;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateTemplateBodyUseCaseImpl implements UpdateTemplateBodyUseCase {
    private final TemplateRepositoryPort repository;

    @Override
    public void execute(UpdateTemplateBodyInput inputModel) {
        //Load dữ liệu
        var templateName = new TemplateName(inputModel.templateName());
        var newBody = new TemplateBody(inputModel.newBody());
        var template = repository.getByName(templateName);
        // Xử lý
        template.updateBody(newBody);
        //Gọi repository
        repository.updateBody(template);
    }
}
