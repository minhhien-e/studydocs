package studydocs.notificationservice.application.port.input.usecase.template.update;


import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.UpdateTemplateSubjectInputModel;

public interface UpdateTemplateSubjectUseCase {
    void execute(UpdateTemplateSubjectInputModel inputModel);

}
