package studydocs.notificationservice.application.usecase.recipient.update;

import studydocs.notificationservice.application.dto.input.recipient.update.MarkAsReadInput;

public interface MarkAsReadUseCase {
    void execute(MarkAsReadInput inputModel);

}
