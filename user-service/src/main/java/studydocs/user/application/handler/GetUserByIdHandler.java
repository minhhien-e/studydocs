package studydocs.user.application.handler;

import studydocs.user.domain.command.GetUserById;
import studydocs.user.domain.dto.UserDTO;
import studydocs.user.domain.entity.UserEntity;
import studydocs.user.domain.service.UserDomainService;
import studydocs.user.helper.HelperMap;
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
