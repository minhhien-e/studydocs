package studydocs.notificationservice.application.usecase.template.update;


import studydocs.notificationservice.application.dto.input.template.update.concrete.UpdateTemplateBodyInput;

public interface UpdateTemplateBodyUseCase {
    void execute(UpdateTemplateBodyInput inputModel);

}
