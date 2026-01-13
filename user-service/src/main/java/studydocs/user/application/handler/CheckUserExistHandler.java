package studydocs.user.application.handler;

import studydocs.user.domain.command.CheckUserExists;
import studydocs.user.domain.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckUserExistHandler implements CommandHandler<CheckUserExists,Boolean> {

    private final UserDomainService userDomainService;

    @Override
    public Boolean  handle(CheckUserExists command) {
        return userDomainService.checkUserExist(command);
    }

    @Override
    public Class<CheckUserExists> commandType() {
        return CheckUserExists.class;
    }
}
