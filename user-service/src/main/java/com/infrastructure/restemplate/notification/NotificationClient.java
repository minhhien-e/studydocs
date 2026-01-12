package com.infrastructure.restemplate.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.interfaces.model.NotifyRegisterSuccessRequest;

public interface NotificationClient {
    void notifyRegisterSuccess(NotifyRegisterSuccessRequest request) throws JsonProcessingException;
}
