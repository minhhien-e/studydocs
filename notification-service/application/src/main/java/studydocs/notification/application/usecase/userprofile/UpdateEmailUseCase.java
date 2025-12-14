package studydocs.notification.application.usecase.userprofile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.userprofile.UpdateEmailCommand;
import studydocs.notification.application.port.in.usecase.userprofile.UpdateEmailUseCasePort;
import studydocs.notification.domain.repository.UserNotificationProfileRepository;

@Service
@RequiredArgsConstructor
public class UpdateEmailUseCase implements UpdateEmailUseCasePort {
    private final UserNotificationProfileRepository repository;

    @Override
    public Void execute(UpdateEmailCommand command) {
        var profile = repository.getByUserId(command.userId());
        profile.updateEmail(command.newEmail());
        repository.save(profile);
        return null;
    }
}
