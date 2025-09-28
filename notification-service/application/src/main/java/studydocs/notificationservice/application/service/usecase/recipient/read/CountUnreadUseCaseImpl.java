package studydocs.notificationservice.application.service.usecase.recipient.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.recipient.read.CountUnreadInput;
import studydocs.notificationservice.application.usecase.recipient.read.CountUnreadUseCase;
import studydocs.notificationservice.domain.repository.RecipientRepositoryPort;

@Service
@RequiredArgsConstructor
@Transactional
public class CountUnreadUseCaseImpl implements CountUnreadUseCase {
    private final RecipientRepositoryPort repository;

    @Override
    public int execute(CountUnreadInput inputModel) {
        return repository.countUnread(inputModel.getRecipientId());
    }
}
