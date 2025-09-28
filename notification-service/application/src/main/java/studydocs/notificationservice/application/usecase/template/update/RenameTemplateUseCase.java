package studydocs.notificationservice.application.usecase.template.update;


import studydocs.notificationservice.application.dto.input.template.update.RenameTemplateInput;

public interface RenameTemplateUseCase {
    void execute(RenameTemplateInput inputModel);
}
