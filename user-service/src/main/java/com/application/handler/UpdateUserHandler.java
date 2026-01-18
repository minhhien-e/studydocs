package com.application.handler;

import com.domain.command.UpdateUser;
import com.domain.dto.UserDTO;
import com.domain.service.UserDomainService;
import com.helper.HelperMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateUserHandler implements CommandHandler<UpdateUser, UserDTO> {

    private final UserDomainService userDomainService;

    @Override
    public UserDTO handle(UpdateUser command) {
        return HelperMap.INSTANCE.userToDTO(userDomainService.updateUser(command));
    }

    @Override
    public Class<UpdateUser> commandType() {
        return UpdateUser.class;
    }
}
