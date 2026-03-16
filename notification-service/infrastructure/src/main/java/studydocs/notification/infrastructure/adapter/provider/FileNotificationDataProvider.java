package studydocs.notification.infrastructure.adapter.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.payload.FileDataProviderPayload;
import studydocs.notification.application.enums.NotificationDataProviderPrefix;
import studydocs.notification.application.port.out.provider.NotificationDataProvider;
import studydocs.notification.application.port.out.repository.FileQueries;
import studydocs.notification.infrastructure.adapter.provider.helper.DataProviderBuilder;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class FileNotificationDataProvider implements NotificationDataProvider<FileDataProviderPayload> {
    private final FileQueries fileQueries;
    private final DataProviderBuilder dataProviderBuilder;

    @Override
    public String getSupportPrefix() {
        return NotificationDataProviderPrefix.FILE.getPrefix();
    }

    @Override
    public String getGroupName() {
        return "Thông tin tệp";
    }

    @Override
    public Map<String, Object> getData(FileDataProviderPayload payload) {
        var file = fileQueries.getById(payload.fileId());
        return dataProviderBuilder.buildDataProvider(file, getSupportPrefix());
    }

    @Override
    public Map<String, String> getAvailableMetadata() {
        return Map.of(
                "$file.name", "Tên tệp tin",
                "$file.size", "Kích cỡ tệp tin",
                "$file.totalPage", "Tổng số trang"
        );
    }
}
