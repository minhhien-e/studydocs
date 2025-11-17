package com.application.handler;

import com.domain.command.GetUserById;
import com.domain.dto.UserDTO;
import com.domain.entity.UserEntity;
import com.domain.service.UserDomainService;
import com.helper.HelperMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserByIdHandler implements CommandHandler<GetUserById, UserDTO> {

    private final UserDomainService userDomainService;

    @Override
    public UserDTO handle(GetUserById command) {
        UserEntity user = userDomainService.getUserById(command);
        return HelperMap.INSTANCE.userToDTO(user);
    }

    @Override
    public Class<GetUserById> commandType() {
        return GetUserById.class;
    }
}
