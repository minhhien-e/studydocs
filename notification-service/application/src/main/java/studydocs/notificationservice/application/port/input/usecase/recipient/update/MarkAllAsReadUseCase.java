package studydocs.notificationservice.application.port.input.usecase.recipient.update;

import studydocs.notificationservice.application.port.input.dto.inputmodel.recipient.update.MarkAllAsReadInputModel;

public interface MarkAllAsReadUseCase {
    void execute(MarkAllAsReadInputModel inputModel);
}
