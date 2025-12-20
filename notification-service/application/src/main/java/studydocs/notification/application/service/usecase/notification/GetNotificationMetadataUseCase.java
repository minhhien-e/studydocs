package studydocs.notification.application.service.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.projection.NotificationMetadataProjection;
import studydocs.notification.application.dto.query.notification.GetNotificationMetadataQuery;
import studydocs.notification.application.port.out.provider.NotificationDataProvider;
import studydocs.notification.application.port.in.usecase.notification.GetNotificationMetadataUseCasePort;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetNotificationMetadataUseCase implements GetNotificationMetadataUseCasePort {
    private final List<NotificationDataProvider> dataProviders;

    @Override
    public List<NotificationMetadataProjection> execute(GetNotificationMetadataQuery query) {
        return dataProviders.stream()
                .collect(Collectors.groupingBy(
                        NotificationDataProvider::getGroupName,
                        Collectors.mapping(
                                NotificationDataProvider::getAvailableMetadata,
                                Collectors.reducing(new java.util.HashMap<String, String>(), (a, b) -> {
                                    a.putAll(b);
                                    return a;
                                })
                        )
                ))
                .entrySet().stream()
                .map(entry -> new NotificationMetadataProjection(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
