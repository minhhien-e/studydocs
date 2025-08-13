package studydocs.notificationservice.application.usecase.recipient.update;

import studydocs.notificationservice.application.dto.input.recipient.update.MarkAllAsReadInput;

public interface MarkAllAsReadUseCase {
    void execute(MarkAllAsReadInput inputModel);
}
