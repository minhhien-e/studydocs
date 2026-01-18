package com.application.handler;

import com.domain.command.GetUsersInRange;
import com.domain.command.RegisterUser;
import com.domain.dto.UserDTO;
import com.domain.service.UserDomainService;
import com.error.exception.HttpExeption;
import com.helper.HelperMap;
import com.infrastructure.restemplate.notification.NotificationClient;
import com.interfaces.model.NotifyRegisterSuccessRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

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
                throw e;
            }
        }

        return result;
    }

    @Override
    public Class<RegisterUser> commandType() {
        return RegisterUser.class;
    }
}
