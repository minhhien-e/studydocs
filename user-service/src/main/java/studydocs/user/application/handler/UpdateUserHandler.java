package studydocs.user.application.handler;

import studydocs.user.domain.command.UpdateUser;
import studydocs.user.domain.dto.UserDTO;
import studydocs.user.domain.service.UserDomainService;
import studydocs.user.helper.HelperMap;
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
