package studydocs.notificationservice.application.port.input.usecase.template.add;

import studydocs.notificationservice.application.port.input.inputmodel.template.add.AddTemplateInputModel;

public interface AddTemplateUseCase {
    void execute(AddTemplateInputModel inputModel);
}
