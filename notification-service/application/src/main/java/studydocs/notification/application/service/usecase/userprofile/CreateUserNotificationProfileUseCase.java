package studydocs.notification.application.service.usecase.userprofile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.userprofile.CreateUserNotificationProfileCommand;
import studydocs.notification.application.port.in.usecase.userprofile.CreateUserNotificationProfileUseCasePort;
import studydocs.notification.domain.aggregate.UserNotificationProfile;
import studydocs.notification.domain.policy.UniqueUserProfilePolicy;
import studydocs.notification.domain.repository.UserNotificationProfileRepository;

@Service
@RequiredArgsConstructor
public class CreateUserNotificationProfileUseCase implements CreateUserNotificationProfileUseCasePort {
    private final UserNotificationProfileRepository repository;
    private final UniqueUserProfilePolicy policy;

    @Override
    public Void execute(CreateUserNotificationProfileCommand command) {
        policy.checkUnique(command.userId());
        var profile = UserNotificationProfile.create(
                command.userId(),
                command.email(),
                command.phoneNumber()
        );

        repository.save(profile);
        return null;
    }
}
