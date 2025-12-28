package com.infrastructure.restemplate.notification.impl;

import com.infrastructure.restemplate.RemoteApiCaller;
import com.infrastructure.restemplate.notification.NotificationClient;
import com.interfaces.model.NotifyRegisterSuccessRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationClientImpl implements NotificationClient {

    private final RemoteApiCaller remoteApiCaller;

    @Value("${notification.service.url}")
    private String notificationServiceUrl;

    @Override
    public void notifyRegisterSuccess(NotifyRegisterSuccessRequest request) {
        remoteApiCaller.postWithoutResponse(
                notificationServiceUrl + "/api/notifications/register-success",
                request
        );
    }
}
