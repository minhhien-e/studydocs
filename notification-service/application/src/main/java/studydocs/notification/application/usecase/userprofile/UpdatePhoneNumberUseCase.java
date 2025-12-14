package studydocs.notification.application.usecase.userprofile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.userprofile.UpdatePhoneNumberCommand;
import studydocs.notification.application.port.in.usecase.userprofile.UpdatePhoneNumberUseCasePort;
import studydocs.notification.domain.repository.UserNotificationProfileRepository;

@Service
@RequiredArgsConstructor
public class UpdatePhoneNumberUseCase implements UpdatePhoneNumberUseCasePort {
    private final UserNotificationProfileRepository repository;

    @Override
    public Void execute(UpdatePhoneNumberCommand command) {
        var profile = repository.getByUserId(command.userId());
        profile.updatePhoneNumber(command.newPhoneNumber());
        repository.save(profile);
        return null;
    }
}
