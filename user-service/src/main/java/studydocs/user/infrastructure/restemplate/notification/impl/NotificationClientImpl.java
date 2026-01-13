package studydocs.user.infrastructure.restemplate.notification.impl;

import studydocs.user.error.exception.HttpException;
import com.fasterxml.jackson.databind.ObjectMapper;
import studydocs.user.infrastructure.restemplate.RemoteApiCaller;
import studydocs.user.infrastructure.restemplate.notification.NotificationClient;
import studydocs.user.interfaces.model.ApiResponse;
import studydocs.user.interfaces.model.NotifyRegisterSuccessRequest;
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
    public void notifyRegisterSuccess(NotifyRegisterSuccessRequest request)  {
//         Convert DTO sang JSON

//            String jsonBody;
//            try {
//                jsonBody = objectMapper.writeValueAsString(request);
//            }catch (JsonProcessingException e) {
//                log.error("lỗi khởi tạo jsonbody"+e.getMessage());
//            }
        try {
            // Gọi RemoteApiCaller (nếu RemoteApiCaller hỗ trợ HttpEntity)
            ApiResponse<Object> res = remoteApiCaller.post(
                    notificationServiceUrl,
                    request,
                    MediaType.APPLICATION_JSON,
                    new ParameterizedTypeReference<>() {
                    }
            );
            if (res.errorCode() != null) {
                log.error("lỗi khi gọi đến notifycation");
                throw new HttpException(res.statusCode(), res.errorCode());
            }
        } catch (Exception ex) {
            log.error("lỗi khi gọi service thông báo: {}", ex.getMessage());
        }
        log.info("Thông báo tạm: đã gửi request đến notification");
    }
}
