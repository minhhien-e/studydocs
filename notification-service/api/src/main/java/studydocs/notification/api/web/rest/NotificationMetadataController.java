package studydocs.notification.api.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studydocs.notification.api.dto.request.notification.GetNotificationMetadataRequest;
import studydocs.notification.api.helper.RequestExecutor;
import studydocs.notification.api.mapper.NotificationMapper;
import studydocs.notification.application.dto.projection.NotificationMetadataProjection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications/metadata")
public class NotificationMetadataController {
    private final RequestExecutor requestExecutor;

    @GetMapping
    public ResponseEntity<?> getMetadata() {
        var request = new GetNotificationMetadataRequest();
        return requestExecutor.executeAndMapView(
                NotificationMapper::toQuery,
                request,
                projection -> NotificationMapper.toView((NotificationMetadataProjection) projection),
                HttpStatus.OK
        );
    }
}
