package studydocs.notificationservice.application.port.input.usecase.template.update;


import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete.UpdateTemplateBodyInputModel;

public interface UpdateTemplateBodyUseCase {
    void execute(UpdateTemplateBodyInputModel inputModel);

}
