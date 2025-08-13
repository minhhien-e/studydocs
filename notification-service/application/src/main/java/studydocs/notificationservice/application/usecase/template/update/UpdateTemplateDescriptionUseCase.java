package studydocs.notificationservice.application.usecase.template.update;


import studydocs.notificationservice.application.dto.input.template.update.concrete.UpdateTemplateDescriptionInput;

public interface UpdateTemplateDescriptionUseCase {
    void execute(UpdateTemplateDescriptionInput inputModel);

}
