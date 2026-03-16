package com.application.handler;

import com.domain.command.GetUsersInRange;
import com.domain.dto.UserDTO;
import com.domain.entity.UserEntity;
import com.domain.service.UserDomainService;
import com.helper.HelperMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetUsersInRangeHandler implements CommandHandler<GetUsersInRange, List<UserDTO>> {

    private final UserDomainService userDomainService;

    @Override
    public List<UserDTO> handle(GetUsersInRange command) {
        List<UserEntity> users = userDomainService.getUsersInRange(
                command.getFromIndex(),
                command.getToIndex()
        );

        return users.stream()
                .map(HelperMap.INSTANCE::userToDTO)
                .toList();
    }

    @Override
    public Class<GetUsersInRange> commandType() {
        return GetUsersInRange.class;
    }
}
