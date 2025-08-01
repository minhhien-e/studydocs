package studydocs.notificationservice.application.port.input.usecase.recipient.update;

import studydocs.notificationservice.application.port.input.dto.inputmodel.recipient.update.MarkAsReadInputModel;

public interface MarkAsReadUseCase {
    void execute(MarkAsReadInputModel inputModel);

}
