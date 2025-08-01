package studydocs.notificationservice.application.port.input.usecase.template.update;


import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete.UpdateTemplateDescriptionInputModel;

public interface UpdateTemplateDescriptionUseCase {
    void execute(UpdateTemplateDescriptionInputModel inputModel);

}
