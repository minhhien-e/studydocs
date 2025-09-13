package studydocs.notificationservice.application.service.usecase.recipient.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.recipient.create.AddRecipientInput;
import studydocs.notificationservice.application.usecase.recipient.create.AddRecipientUseCase;
import studydocs.notificationservice.domain.repository.NotificationRecipientRepositoryPort;

@Service
@RequiredArgsConstructor
@Transactional
public class AddRecipientUseCaseImpl implements AddRecipientUseCase {
    private final NotificationRecipientRepositoryPort repository;

    @Override
    public void execute(AddRecipientInput inputModel) {
        repository.save(inputModel.toDomain());
    }
}
