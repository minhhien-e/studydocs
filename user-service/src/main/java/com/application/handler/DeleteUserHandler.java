package com.application.handler;

import com.domain.command.DeleteUser;
import com.domain.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteUserHandler implements CommandHandler<DeleteUser, Boolean> {

    private final UserDomainService userDomainService;

    @Override
    public Boolean handle(DeleteUser command) {
        return userDomainService.deleteUser(command.getUserId());
    }

    @Override
    public Class<DeleteUser> commandType() {
        return DeleteUser.class;
    }
}
