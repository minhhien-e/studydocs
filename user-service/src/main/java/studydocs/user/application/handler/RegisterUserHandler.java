package studydocs.user.application.handler;

import studydocs.user.domain.command.RegisterUser;
import studydocs.user.domain.dto.UserDTO;
import studydocs.user.domain.service.UserDomainService;
import studydocs.user.helper.HelperMap;
import studydocs.user.infrastructure.restemplate.notification.NotificationClient;
import studydocs.user.interfaces.model.NotifyRegisterSuccessRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class RegisterUserHandler implements CommandHandler<RegisterUser,UserDTO>{

    private final UserDomainService userDomainService;
    private final NotificationClient notificationClient;

    @Override
    public UserDTO handle(RegisterUser command) {

        UserDTO result = HelperMap.INSTANCE
                .userToDTO(userDomainService.registerUser(command));

        if (result != null) {
            try {
                notificationClient.notifyRegisterSuccess(
                        new NotifyRegisterSuccessRequest(
                                result.getEmail(),
                                result.getPhoneNumber(),
                                result.getEmail(),
                                result.getPhoneNumber()
                        )
                );
            } catch (Exception e) {
                log.error("Notify failed", e);
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public Class<RegisterUser> commandType() {
        return RegisterUser.class;
    }
}
