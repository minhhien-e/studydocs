package studydocs.notificationservice.application.service.usecase.template.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.template.update.UpdateTemplateSubjectInput;
import studydocs.notificationservice.application.usecase.template.update.UpdateTemplateSubjectUseCase;
import studydocs.notificationservice.domain.model.valueobject.name.TemplateName;
import studydocs.notificationservice.domain.model.valueobject.template.TemplateSubject;
import studydocs.notificationservice.domain.repository.TemplateRepositoryPort;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateTemplateSubjectUseCaseImpl implements UpdateTemplateSubjectUseCase {
    private final TemplateRepositoryPort repository;

    @Override
    public void execute(UpdateTemplateSubjectInput inputModel) {
        //Load dữ liệu
        var templateName = new TemplateName(inputModel.templateName());
        var newSubject = new TemplateSubject(inputModel.newSubject());
        var template = repository.getByName(templateName);
        // Xử lý
        template.updateSubject(newSubject);
        //Gọi repository
        repository.updateSubject(template);
    }
}
