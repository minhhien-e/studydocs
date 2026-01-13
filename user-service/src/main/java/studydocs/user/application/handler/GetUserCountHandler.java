package studydocs.user.application.handler;

import studydocs.user.domain.command.GetUserCount;
import studydocs.user.domain.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserCountHandler implements CommandHandler<GetUserCount, Integer> {

    private final UserDomainService userDomainService;

    @Override
    public Integer handle(GetUserCount command) {
        return userDomainService.getUserCount();
    }

    @Override
    public Class<GetUserCount> commandType() {
        return GetUserCount.class;
    }
}
