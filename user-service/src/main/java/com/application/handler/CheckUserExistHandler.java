package com.application.handler;

import com.domain.command.CheckUserExists;
import com.domain.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckUserExistHandler implements CommandHandler<CheckUserExists,Boolean> {

    private final UserDomainService userDomainService;

    @Override
    public Boolean  handle(CheckUserExists command) {
        // ✅ Ủy quyền xử lý logic sang domain service
        return userDomainService.checkUserExist(command);
    }

    @Override
    public Class<CheckUserExists> commandType() {
        return CheckUserExists.class;
    }
}
