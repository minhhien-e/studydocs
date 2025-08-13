package studydocs.notificationservice.application.usecase.template.update;


import studydocs.notificationservice.application.dto.input.template.update.concrete.UpdateTemplateSubjectInput;

public interface UpdateTemplateSubjectUseCase {
    void execute(UpdateTemplateSubjectInput inputModel);

}
