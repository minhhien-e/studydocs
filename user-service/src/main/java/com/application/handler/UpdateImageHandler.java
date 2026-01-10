package com.application.handler;

import com.domain.command.UpdateImage;
import com.domain.dto.UserDTO;
import com.domain.service.UserDomainService;
import com.helper.HelperMap;
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
