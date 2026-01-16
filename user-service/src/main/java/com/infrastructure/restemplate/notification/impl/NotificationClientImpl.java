package com.infrastructure.restemplate.notification.impl;

import com.error.exception.HttpExeption;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infrastructure.restemplate.RemoteApiCaller;
import com.infrastructure.restemplate.notification.NotificationClient;
import com.interfaces.model.ApiResponse;
import com.interfaces.model.NotifyRegisterSuccessRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationClientImpl implements NotificationClient {

    private final RemoteApiCaller remoteApiCaller;
    private final ObjectMapper objectMapper; // inject ObjectMapper

    @Value("${notification.service.url}")
    private String notificationServiceUrl;

    @Override
    public void notifyRegisterSuccess(NotifyRegisterSuccessRequest request) {
        // Convert DTO sang JSON
//            String jsonBody = objectMapper.writeValueAsString(request);

        try {
            // Gọi RemoteApiCaller (nếu RemoteApiCaller hỗ trợ HttpEntity)
            ApiResponse<Object> res = remoteApiCaller.post(
                    notificationServiceUrl,
                    request,
                    MediaType.APPLICATION_JSON,
                    new ParameterizedTypeReference<ApiResponse<Object>>() {
                    }
            );
            if (res.errorCode() != null) {
                log.error("lỗi khi gọi đến notifycation");
                throw new HttpExeption(res.statusCode(), res.errorCode());
            }
        } catch (Exception ex) {
            log.error("lỗi khi gọi service thông báo: {}", ex.getMessage());
        }

    }
}
