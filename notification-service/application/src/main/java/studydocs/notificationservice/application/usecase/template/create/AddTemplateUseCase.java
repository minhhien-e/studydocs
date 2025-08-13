package studydocs.notificationservice.application.usecase.template.create;

import studydocs.notificationservice.application.dto.input.template.create.AddTemplateInput;

public interface AddTemplateUseCase {
    void execute(AddTemplateInput inputModel);
}
