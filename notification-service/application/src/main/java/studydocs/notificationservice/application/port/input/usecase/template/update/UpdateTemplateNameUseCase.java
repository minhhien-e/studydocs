package studydocs.notificationservice.application.port.input.usecase.template.update;


import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete.UpdateTemplateNameInputModel;

public interface UpdateTemplateNameUseCase {
    void execute(UpdateTemplateNameInputModel inputModel);

}
