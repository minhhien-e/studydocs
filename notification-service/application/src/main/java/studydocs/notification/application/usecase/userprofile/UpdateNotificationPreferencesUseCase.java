package studydocs.notification.application.usecase.userprofile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.userprofile.UpdateNotificationPreferencesCommand;
import studydocs.notification.application.port.in.usecase.userprofile.UpdateNotificationPreferencesUseCasePort;
import studydocs.notification.domain.repository.UserNotificationProfileRepository;

@Service
@RequiredArgsConstructor
public class UpdateNotificationPreferencesUseCase implements UpdateNotificationPreferencesUseCasePort {
    private final UserNotificationProfileRepository repository;

    @Override
    public Void execute(UpdateNotificationPreferencesCommand command) {
        var profile = repository.getByUserId(command.userId());
        profile.setPushEnabled(command.pushEnabled());
        profile.setEmailEnabled(command.emailEnabled());
        profile.setSmsEnabled(command.smsEnabled());
        repository.save(profile);
        return null;
    }
}
