package studydocs.notificationservice.application.service.usecase.recipient.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.recipient.create.CountUnreadInput;
import studydocs.notificationservice.application.usecase.recipient.read.CountUnreadUseCase;
import studydocs.notificationservice.domain.repository.NotificationRecipientRepositoryPort;

@Service
@RequiredArgsConstructor
@Transactional
public class CountUnreadUseCaseImpl implements CountUnreadUseCase {
    private final NotificationRecipientRepositoryPort repository;

    @Override
    public int execute(CountUnreadInput inputModel) {
        return repository.countUnread(inputModel.getRecipientId());
    }
}
