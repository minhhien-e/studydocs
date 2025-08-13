package studydocs.notificationservice.application.usecase.recipient.create;

import studydocs.notificationservice.application.dto.input.recipient.create.AddRecipientInput;

public interface AddRecipientUseCase {
    void execute(AddRecipientInput inputModel);
}
