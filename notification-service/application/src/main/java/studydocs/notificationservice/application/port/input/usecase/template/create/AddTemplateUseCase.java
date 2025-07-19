package studydocs.notificationservice.application.port.input.usecase.template.create;

import studydocs.notificationservice.application.port.input.dto.inputmodel.template.create.AddTemplateInputModel;

public interface AddTemplateUseCase {
    void execute(AddTemplateInputModel inputModel);
}
