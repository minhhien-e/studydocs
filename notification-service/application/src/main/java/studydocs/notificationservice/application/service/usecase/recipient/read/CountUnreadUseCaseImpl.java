package studydocs.notificationservice.application.service.usecase.recipient.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.dto.input.recipient.create.CountUnreadInput;
import studydocs.notificationservice.application.usecase.recipient.read.CountUnreadUseCase;
import studydocs.notificationservice.domain.repository.NotificationRecipientRepositoryPort;
import studydocs.notificationservice.shared.exception.abstracts.ForbiddenException;

@Service
@RequiredArgsConstructor
public class CountUnreadUseCaseImpl implements CountUnreadUseCase {
    private final NotificationRecipientRepositoryPort repository;

    @Override
    public int execute(CountUnreadInput inputModel) {
        if (!inputModel.getRecipientId().equals(inputModel.getRequesterId()))
            throw new ForbiddenException();
        return repository.countUnread(inputModel.getRecipientId());
    }
}
