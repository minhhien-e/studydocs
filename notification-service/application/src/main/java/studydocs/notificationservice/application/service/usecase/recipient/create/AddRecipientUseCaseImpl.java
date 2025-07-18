package studydocs.notificationservice.application.service.usecase.recipient.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.port.input.dto.inputmodel.recipient.create.AddRecipientInputModel;
import studydocs.notificationservice.application.port.input.usecase.recipient.create.AddRecipientUseCase;
import studydocs.notificationservice.application.port.ouput.repository.NotificationRecipientRepositoryPort;

@Service
@RequiredArgsConstructor
public class AddRecipientUseCaseImpl implements AddRecipientUseCase {
    private final NotificationRecipientRepositoryPort repository;
    @Override
    public void execute(AddRecipientInputModel inputModel) {
        repository.save(inputModel.toDomain());
    }
}
