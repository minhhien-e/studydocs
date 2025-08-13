package studydocs.notificationservice.application.usecase.recipient.read;

import studydocs.notificationservice.application.dto.input.recipient.create.CountUnreadInput;

public interface CountUnreadUseCase {
    int execute(CountUnreadInput inputModel);
}
