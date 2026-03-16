package com.infrastructure.restemplate.notification;

import com.interfaces.model.NotifyRegisterSuccessRequest;

public interface NotificationClient {
    void notifyRegisterSuccess(NotifyRegisterSuccessRequest request);
}
