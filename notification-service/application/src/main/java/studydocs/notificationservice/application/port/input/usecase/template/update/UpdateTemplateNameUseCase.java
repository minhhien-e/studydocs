package studydocs.notificationservice.application.port.input.usecase.template.update;


import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.UpdateTemplateNameInputModel;

public interface UpdateTemplateNameUseCase {
    void execute(UpdateTemplateNameInputModel inputModel);

}
