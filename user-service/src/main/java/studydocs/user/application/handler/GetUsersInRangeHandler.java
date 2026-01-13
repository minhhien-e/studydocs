package studydocs.user.application.handler;

import studydocs.user.domain.command.GetUsersInRange;
import studydocs.user.domain.dto.UserDTO;
import studydocs.user.domain.entity.UserEntity;
import studydocs.user.domain.service.UserDomainService;
import studydocs.user.helper.HelperMap;
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
