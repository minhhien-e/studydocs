package studydocs.user.application.handler;

import studydocs.user.domain.command.CheckUserPrivate;
import studydocs.user.domain.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckUserPrivateHandler implements CommandHandler<CheckUserPrivate, Boolean> {

    private final UserDomainService userDomainService;

    @Override
    public Boolean handle(CheckUserPrivate command) {
        // Ủy quyền xử lý logic sang domain service
        return userDomainService.checkUserPrivate(command);
    }

    @Override
    public Class<CheckUserPrivate> commandType() {
        return CheckUserPrivate.class;
    }
}
