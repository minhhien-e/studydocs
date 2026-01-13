package studydocs.user.application.handler;

import studydocs.user.domain.command.UpdateImage;
import studydocs.user.domain.dto.UserDTO;
import studydocs.user.domain.service.UserDomainService;
import studydocs.user.helper.HelperMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateImageHandler implements CommandHandler<UpdateImage, UserDTO> {

    private final UserDomainService userDomainService;

    @Override
    public UserDTO handle(UpdateImage command) {
        return HelperMap.INSTANCE.userToDTO(userDomainService.updateImage(command.getUserId(), command.getImage()));
    }

    @Override
    public Class<UpdateImage> commandType() {
        return UpdateImage.class;
    }
}
