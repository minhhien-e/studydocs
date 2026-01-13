package studydocs.user.application.handler;

import studydocs.user.domain.command.GetAllUser;
import studydocs.user.domain.dto.UserDTO;
import studydocs.user.domain.entity.UserEntity;
import studydocs.user.domain.service.UserDomainService;
import studydocs.user.helper.HelperMap;
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
