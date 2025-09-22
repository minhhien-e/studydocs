package studydocs.notificationservice.application.service.usecase.template.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.template.create.AddTemplateInput;
import studydocs.notificationservice.application.usecase.template.create.AddTemplateUseCase;
import studydocs.notificationservice.domain.factory.abstracts.TemplateFactory;
import studydocs.notificationservice.domain.repository.TemplateRepositoryPort;

@Service
@RequiredArgsConstructor
@Transactional
public class AddTemplateUseCaseImpl implements AddTemplateUseCase {
    private final TemplateRepositoryPort repository;
    private final TemplateFactory templateFactory;

    @Override
    public void execute(AddTemplateInput inputModel) {
        //Tạo dữ liệu
        var templateName = inputModel.name();
        var templateChannel = inputModel.channel();
        var subjectTemplate = inputModel.subjectTemplate();
        var bodyTemplate = inputModel.bodyTemplate();
        var description = inputModel.description();
        //Xử lý logic
        var template = templateFactory.create(templateName, templateChannel, subjectTemplate, bodyTemplate, description);
        //Gọi repository
        repository.save(template);
    }
}
