package studydocs.notificationservice.application.port.input.usecase.recipient.create;

import studydocs.notificationservice.application.port.input.dto.inputmodel.recipient.create.AddRecipientInputModel;

public interface AddRecipientUseCase {
    void execute(AddRecipientInputModel inputModel);
}
