package com.application.handler;

import com.domain.command.RegisterUser;
import com.domain.dto.UserDTO;
import com.domain.entity.UserEntity;
import com.domain.service.UserDomainService;
import com.helper.HelperMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterUserHandler implements CommandHandler<RegisterUser, UserDTO> {

    private final UserDomainService userDomainService;

    @Override
    public UserDTO handle(RegisterUser command) {
        return HelperMap.INSTANCE.userToDTO(userDomainService.registerUser(command));
    }

    @Override
    public Class<RegisterUser> commandType() {
        return RegisterUser.class;
    }
}
