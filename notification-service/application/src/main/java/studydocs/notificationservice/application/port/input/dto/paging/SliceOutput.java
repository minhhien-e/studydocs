package studydocs.notificationservice.application.port.input.dto.paging;

import java.util.List;

public record SliceOutput<T>(List<T> content, boolean hasNext) {
}
