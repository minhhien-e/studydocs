package studydocs.notificationservice.application.port.input.usecase.recipient.read;

import studydocs.notificationservice.application.port.input.dto.inputmodel.recipient.create.CountUnreadInputModel;

public interface CountUnreadUseCase {
    int execute(CountUnreadInputModel inputModel);
}
