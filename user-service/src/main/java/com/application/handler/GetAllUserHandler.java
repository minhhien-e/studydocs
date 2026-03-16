package com.application.handler;

import com.domain.command.GetAllUser;
import com.domain.dto.UserDTO;
import com.domain.entity.UserEntity;
import com.domain.service.UserDomainService;
import com.helper.HelperMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllUserHandler implements CommandHandler<GetAllUser, List<UserDTO>> {

    private final UserDomainService userDomainService;

    @Override
    public List<UserDTO> handle(GetAllUser command) {
        List<UserEntity> users = userDomainService.getAllUsers();
        return users.stream()
                .map(HelperMap.INSTANCE::userToDTO)
                .toList();
    }

    @Override
    public Class<GetAllUser> commandType() {
        return GetAllUser.class;
    }
}
